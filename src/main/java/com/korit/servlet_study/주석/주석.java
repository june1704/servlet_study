package com.korit.servlet_study.주석;

public class 주석 {
    /**
     * 톰캣의 흐름
     * tomcat main()
     * Request req = new Request();
     * Response resp = new Response();
     * aFilter(req, resp);
     * - 마지막
     *
     * void aFilter(Request req, Response resp)
     * 한글인코딩 - 전처리
     * cors 처리 - 전처리
     * bServlet(req, resp);
     * -후처리
     *
     * void bServlet(Request req, Response resp)
     * 서비스();
     * 리포지토리();
     * req
     * resp
     *
     *----------------------------------------------
     * 클라이언트 - 톰캣(http://localhost:8080) - 메서드 호출 후 호출한 지점으로 이동
     * 호출할 때 Request, Response 받아옴
     *
     * 리액트 - 톰캣 - 필터 - 서블렛 - 서비스 - 리포지터리 - db
     *
     * 미니 게시판 프로젝트
     * 1. 로그인 했을 때 사용자 정보가 바뀌어야 함
     * - 바뀌는 이유 MainHeader의 userId 삼항연산자 때문에
     * queryClient.getQueryData(["authenticatedUserQuery"])?.data.body;
     * const queryClient = useQueryClient();
     * const queryClient = new QueryClient();
     * <RecoilRoot>
     *     <QueryClientProvider client={queryClient}>
     *             <BrowserRouter>
     *                 <App />
     *             </BrowserRouter>
     *         </QueryClientProvider>
     *     </RecoilRoot>
     * queryClient에 저장을 함
     *
     * 2. 로그인
     * Filter를 거치고
     *  - CorsFilter
     *  - EncodingFilter doFilter 전이 전처리 후가 후처리
     * SigninRestServlet의 doPost Request Response - AuthService 참조
     *  - Json문자열을 Dto로 바꿔준다 까지
     *  - AuthService에서 사용자 정보(ID, Password) 비교 및 확인
     *  - jwt 토큰 생성 - JwtProvider 참조 (Jwts를 사용하기 위한 dependency 추가)
     *  JwtProvider에서
     *  - 1년짜리 토큰 생성
     *  - generateToken으로 토큰 생성
     *
     * SigninPage의 axios.post로
     *  - response 응답받음
     *
     * App.js의 재랜더링.
     * - 요청함수 정의
     * const authenticatedUser = async () => {
     *     return await axios.get("http://localhost:8080/servlet_study_war/api/authenticated", {
     *       headers: {
     *         "Authorization": `Bearer ${localStorage.getItem("AccessToken")}`,
     *       }
     *     });
     *   }
     *
     *
     * AuthenticatedServlet으로 감.
     * - parseToken 못쓰는 토큰은 null
     *
     * <MainLayout>
     *         <Routes>
     *           <Route path="/" element={ <IndexPage /> } />
     *           <Route path="/write" element={ <WritePage /> } />
     *           <Route path="/list" element={ <ListPage /> } />
     *           <Route path="/signup" element={ <SignupPage /> } />
     *           <Route path="/signin" element={ <SigninPage /> } />
     *         </Routes>
     *       </MainLayout>
     * 랜더링이 끝나면 다시 메인헤더의 useQueryClient(); 로 돌아옴
     *
     * const getUserQuery = useQuery(
     *         ["getUserQuery", userId],
     *         getUserApi,
     *         {
     *             refetchOnWindowFocus: false,
     *             enabled: !!userId,
     *         }
     *     );
     *
     *
     *
     *
     *
     *
     *
     * */










}
