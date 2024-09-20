import { http, HttpResponse } from 'msw';
interface NicknameRequest {
  nick: string;
}
const mockUser = ['닉네임'];

export const handlers = [
  // get 테스트
  http.get('https://example.com/test', () => {
    // ...and respond to them using this JSON response.
    return HttpResponse.json({
      user: {
        firstName: 'John',
        lastName: 'Maverick',
      },
    });
  }),

  //닉네임 여부 테스트
  http.post('https://example.com/nick', async ({ request }) => {
    const newPost = (await request.json()) as NicknameRequest;
    console.log(newPost);

    if (mockUser.includes(newPost?.nick)) {
      return HttpResponse.json({ isDuplicated: true });
    }
    return HttpResponse.json({ isDuplicated: false });
  }),
];
