package com.ssafy.health.domain.notification.service;

import com.ssafy.health.common.fcm.dto.request.FcmRequestDto;
import com.ssafy.health.common.fcm.service.FcmService;
import com.ssafy.health.domain.account.entity.User;
import com.ssafy.health.domain.account.exception.UserNotFoundException;
import com.ssafy.health.domain.account.repository.UserRepository;
import com.ssafy.health.domain.battle.entity.BattleStatus;
import com.ssafy.health.domain.battle.repository.BattleRepository;
import com.ssafy.health.domain.body.BodyHistory.repository.BodyHistoryRepository;
import com.ssafy.health.domain.notification.dto.request.NotificationRequestDto;
import com.ssafy.health.domain.notification.dto.response.StatusUpdateResponseDto;
import com.ssafy.health.domain.notification.entity.Notification;
import com.ssafy.health.domain.notification.entity.NotificationStatus;
import com.ssafy.health.domain.notification.entity.NotificationType;
import com.ssafy.health.domain.notification.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import static com.ssafy.health.domain.notification.entity.NotificationMessage.*;

@Service
@RequiredArgsConstructor
public class NotificationWriteService {

    private final BattleRepository battleRepository;
    private final BodyHistoryRepository bodyHistoryRepository;
    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;
    private final FcmService fcmService;

    public void createBodySurveyNotification(NotificationRequestDto dto)
            throws ExecutionException, InterruptedException {

        Map<String, Object> additionalData = new HashMap<>();
        LocalDateTime lastSurveyedDate = null;

        try {
            lastSurveyedDate = bodyHistoryRepository.findFirstByUserId(dto.getUserId())
                    .orElseThrow().getCreatedAt();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        additionalData.put("lastSurveyedDate", lastSurveyedDate);

        Notification notification = notificationBuilder(
                dto.getNotificationType(), dto.getUserId(), SURVEY.getMessage(), additionalData);
        notificationRepository.save(notification);

        // 사용자에 등록된 기기가 있을 경우에만 FCM 푸시
        sendFcmMessage(dto.getUserId(), "체형 입력 알림", SURVEY.getMessage());
    }

    public void createBattleNotification(NotificationRequestDto dto, Long battleId)
            throws ExecutionException, InterruptedException {

        Map<String, Object> additionalData = new HashMap<>();
        additionalData.put("battleId", battleId);

        BattleStatus status = battleRepository.findById(battleId).orElseThrow().getStatus();
        String message = (status.equals(BattleStatus.STARTED) ? BATTLE_START.getMessage() : BATTLE_END.getMessage());

        Notification battleNotification = notificationBuilder(
                dto.getNotificationType(), dto.getUserId(), message, additionalData);
        notificationRepository.save(battleNotification);

        // 사용자에 등록된 기기가 있을 경우에만 FCM 푸시
        sendFcmMessage(dto.getUserId(), "배틀 알림", message);
    }

    public StatusUpdateResponseDto updateNotificationStatus(NotificationStatus status, Long notificationId) {
        Notification notification = notificationRepository.findById(notificationId).orElseThrow();
        NotificationStatus previousStatus = notification.getNotificationStatus();
        notification.updateNotificationStatus(status);
        notificationRepository.save(notification);

        return StatusUpdateResponseDto.builder()
                .previousStatus(previousStatus)
                .currentStatus(status)
                .build();
    }

    public void sendFcmMessage(Long userId, String title, String body) throws ExecutionException, InterruptedException {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        String token = user.getDeviceToken();

        if (token != null) {
            FcmRequestDto fcmRequestDto = FcmRequestDto.builder()
                    .token(token)
                    .title(title)
                    .body(body)
                    .build();
            fcmService.sendMessageToDevice(fcmRequestDto);
        }
    }

    public Notification notificationBuilder(NotificationType notificationType, Long userId, String message) {
        return Notification.builder()
                .notificationType(notificationType)
                .notificationStatus(NotificationStatus.UNREAD)
                .content(message)
                .userId(userId)
                .build();
    }

    public Notification notificationBuilder(
            NotificationType notificationType, Long userId, String message, Map<String, Object> additionalData) {
        return Notification.builder()
                .notificationType(notificationType)
                .notificationStatus(NotificationStatus.UNREAD)
                .content(message)
                .userId(userId)
                .additionalData(additionalData)
                .build();
    }
}
