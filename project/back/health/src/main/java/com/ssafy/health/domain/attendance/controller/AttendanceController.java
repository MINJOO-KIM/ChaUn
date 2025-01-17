package com.ssafy.health.domain.attendance.controller;

import com.ssafy.health.common.ApiResponse;
import com.ssafy.health.common.util.MonthlyRequestDto;
import com.ssafy.health.domain.attendance.dto.response.AttendanceListDto;
import com.ssafy.health.domain.attendance.dto.response.AttendanceSuccessDto;
import com.ssafy.health.domain.attendance.service.AttendanceReadService;
import com.ssafy.health.domain.attendance.service.AttendanceWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceWriteService attendanceWriteService;
    private final AttendanceReadService attendanceReadService;

    @PostMapping("/attendance")
    public ApiResponse<AttendanceSuccessDto> markAttendance() throws ExecutionException, InterruptedException {
        return ApiResponse.success(attendanceWriteService.markAttendance());
    }

    @GetMapping("/attendance")
    public ApiResponse<AttendanceListDto> getMonthlyAttendance(MonthlyRequestDto requestDto) {
        return ApiResponse.success(attendanceReadService.getMonthlyAttendance(requestDto));
    }
}
