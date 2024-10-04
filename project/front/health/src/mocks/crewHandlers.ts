import { http, HttpResponse } from 'msw';

// 크루 추천 목록
const crewList = [
  {
    crewId: 1,
    crewName: '달리자',
    exerciseName: '러닝',
    crewProfileImage: 'https://c106-chaun.s3.ap-northeast-2.amazonaws.com/file_uuid.jpeg',
  },
  {
    crewId: 2,
    crewName: '달리자',
    exerciseName: '러닝',
    crewProfileImage: 'https://c106-chaun.s3.ap-northeast-2.amazonaws.com/file_uuid.jpeg',
  },
];

const crewDetail = {
  crewId: 1,
  crewName: '달리는 번개2',
  exerciseName: '러닝',
  profileImage: 'crew-profile-image.jpg',
  description: '번개맨보다 빠른 러너들의 모임',
  crewCoins: 350,
  crewRanking: 3,
  totalBattleCount: 10,
  winCount: 7,
  averageAge: 29,
  activityScore: 1200,
  basicScore: 850,
  role: 'LEADER',
};
const memberList = [
  {
    nickname: '달리기 왕자',
    userId: 20,
    userProfileImage: 'crew-profile-image.jpg',
    exerciseTime: 123123,
  },
  {
    nickname: '달리기 공주',
    userId: 21,
    userProfileImage: 'crew-profile-image.jpg',
    exerciseTime: 123121,
  },
];

const crewBattleStatus = {
  battleId: 1,
  myTeamName: '달리자크루',
  myTeamScore: 1200,
  opponentTeamName: '크크크루',
  opponentTeamScore: 1000,
  exerciseName: '러닝',
  dDay: 2,
  battleStatus: 'STARTED',
};

const sendCoin = {
  message: '코인을 전송하였습니다.',
  crewCoin: 1000,
  myCoin: 900,
};
const CrewQuest = [
  {
    questId: 3,
    title: '크루 내 2명 이상의 팀원 하루에 합산 1시간 이상 운동하기',
    questPeriod: 'DAILY',
    isCompleted: true,
  },
];
const battleStatus = {
  myTeamName: '달리자크루',
  myTeamScore: 400,
  opponentTeamName: '크크크루',
  opponentTeamScore: 500,
  exerciseName: '러닝',
  battleStatus: 'STARTED',
  dDay: 2,
};
const baseUrl = import.meta.env.VITE_APP_BASE_URL;
export const crewHandlers = [
  http.get(`${baseUrl}/users/recommend-crew`, () => {
    // console.log('크루 추천 목록 조회');
    return HttpResponse.json(crewList, { status: 200 });
  }),

  http.get(`${baseUrl}/users/crew-recommendation/:crew_id`, ({ params }) => {
    const { crew_id } = params;
    console.log('크루 추천 상세 모달의 크루 id', crew_id);
    return HttpResponse.json({
      crewName: '달리자',
      exerciseName: '러닝',
      description: '번개보다 빠른 러너들의 모임',
      crewProfileImage: 'crew-profile-image.jpg',
      bodyType: '미정',
      age: 29,
      rank: 31,
      dailyCaloricIntake: 1400,
      basicScore: 1000,
      activityScore: 1000,
      coin: 300,
    });
  }),
  //상세보기
  http.get(`${baseUrl}/crew/:crew_id/detail`, ({ params }) => {
    const { crew_id } = params;
    console.log('크루 상세보기의 크루 id', crew_id);
    return HttpResponse.json(crewDetail, { status: 200 });
  }),
  // 크루 내 랭킹 조회
  http.get(`${baseUrl}/crew/:crew_id/ranking`, ({ params }) => {
    const { crew_id } = params;
    const numberCrewId = Array.isArray(crew_id) ? crew_id[0] : crew_id;

    console.log('크루 내 랭킹 조회의 크루 id', numberCrewId);

    return HttpResponse.json(memberList, { status: 200 });
  }),

  // 운동별 크루 랭킹 조회
  http.get(`${baseUrl}/crew/ranking/:exercise_id`, ({ params }) => {
    const { exercise_id } = params;
    const rankingId = Array.isArray(exercise_id) ? exercise_id[0] : exercise_id;

    console.log('운동별 크루 랭킹 id', rankingId);

    return HttpResponse.json({
      status: 200,
      message: '',
      data: {
        crewList: [
          {
            crewId: 2,
            crewName: '달리자',
            exerciseName: '러닝',
            crewProfileImage: 'crew-profile-image.jpg',
            basicScore: 100,
            activityScore: 200,
          },
          {
            crewId: 3,
            crewName: '달리자',
            exerciseName: '러닝',
            crewProfileImage: 'crew-profile-image.jpg',
            basicScore: 1000,
            activityScore: 2000,
          },
          {
            crewId: 3,
            crewName: '달리자',
            exerciseName: '러닝',
            crewProfileImage: 'crew-profile-image.jpg',
            basicScore: 1000,
            activityScore: 2000,
          },
          {
            crewId: 3,
            crewName: '달리자',
            exerciseName: '러닝',
            crewProfileImage: 'crew-profile-image.jpg',
            basicScore: 1000,
            activityScore: 2000,
          },
          {
            crewId: 3,
            crewName: '달리자',
            exerciseName: '러닝',
            crewProfileImage: 'crew-profile-image.jpg',
            basicScore: 1000,
            activityScore: 2000,
          },
          {
            crewId: 3,
            crewName: '달리자',
            exerciseName: '러닝',
            crewProfileImage: 'crew-profile-image.jpg',
            basicScore: 1000,
            activityScore: 2000,
          },
          {
            crewId: 3,
            crewName: '달리자',
            exerciseName: '러닝',
            crewProfileImage: 'crew-profile-image.jpg',
            basicScore: 1000,
            activityScore: 2000,
          },
          {
            crewId: 3,
            crewName: '달리자',
            exerciseName: '러닝',
            crewProfileImage: 'crew-profile-image.jpg',
            basicScore: 1000,
            activityScore: 2000,
          },
          {
            crewId: 3,
            crewName: '달리자',
            exerciseName: '러닝',
            crewProfileImage: 'crew-profile-image.jpg',
            basicScore: 1000,
            activityScore: 2000,
          },
        ],
      },
    });
  }),

  http.post(`${baseUrl}/crew/:crew_id/join`, ({ params }) => {
    const { crew_id } = params;
    console.log('크루 가입 신청의 크루 id', crew_id);
    return HttpResponse.json({ status: 200, message: '크루 가입 신청 성공' });
  }),
  //가입된 크루 조회
  http.get(`${baseUrl}/users/:userId/crew-list`, ({ params }) => {
    const { userId } = params;
    console.log(userId);

    return HttpResponse.json({
      status: 200,
      message: '',
      data: {
        crewList: [
          {
            crewId: 1,
            crewName: '달리자',
            exerciseName: '러닝',
            crewProfileImage: 'crew-profile-image.jpg',
          },
          {
            crewId: 2,
            crewName: '달리자',
            exerciseName: '러닝',
            crewProfileImage: 'crew-profile-image.jpg',
          },
        ],
      },
    });
  }),

  // 크루 배틀 현황 조회
  http.get(`${baseUrl}/crew/:crew_id/battle`, ({ params }) => {
    const { crew_id } = params;
    console.log('배틀중인 크루id', crew_id);
    return HttpResponse.json(crewBattleStatus, { status: 200 });
  }),

  http.get(`${baseUrl}/quest/get/crew`, ({ params }) => {
    const { crew_id } = params;
    console.log('quest get crew', crew_id);
    return HttpResponse.json(CrewQuest, { status: 200 });
  }),

  // 크루 랜덤 매칭 동의 여부
  http.post(`${baseUrl}/crew/:crew_id/battle/ready`, ({ params }) => {
    const { crew_id } = params;
    console.log('크루 랜덤 매칭 동의여부 크루 id', crew_id);
  }),

  // 코인 전송
  http.post(`${baseUrl}/crew/:crew_id/coin/:coin_count`, ({ params }) => {
    const { crew_id, coin_count } = params;
    console.log('코인 전송', crew_id, coin_count);
    return HttpResponse.json(sendCoin, { status: 200 });
  }),

  //배틀 현황
  http.get(`${baseUrl}/crew/:crew_id/battle`, ({ params }) => {
    const { crew_id } = params;
    console.log('배틀 현황', crew_id);

    return HttpResponse.json(battleStatus, { status: 200 });
  }),
];