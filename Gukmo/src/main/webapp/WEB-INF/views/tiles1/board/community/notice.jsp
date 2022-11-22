<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	String ctxPath = request.getContextPath();
%>



<!-- 직접 만든 CSS 1 -->
<link rel="stylesheet" type="text/css"
	href="<%=ctxPath%>/resources/css/gwangbin/detail.css" />

<script type="text/javascript"
	src="<%=ctxPath%>/resources/js/gwangbin/notice.js"></script>


<script type="text/javascript"
	src="<%=ctxPath%>/resources/jquery/jquery.form.min.js"></script>
	
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/smarteditor/js/HuskyEZCreator.js" charset="utf-8"></script>

<%---------------------------------- 컨테이너 시작 ----------------------------------%>
<div class="container min-w-0 flex-auto">
	<div>

		<%------------------ 카테고리 시작 ------------------%>
		<div class="relative my-7">
			<div class="absolute inset-0 flex items-center">
				<div class="w-full border-t"></div>
			</div>
			<div class="relative ml-2 flex text-sm font-normal sm:ml-5">
				<div class="bg-white px-2">
					<a id="big_category" href="/community">커뮤니티</a> / <a
						id="small_category" href="/community/life">사는 얘기</a>
				</div>
			</div>

		</div>
		<%------------------ 카테고리 끝------------------%>



		<%------------------ 글 전체몸통 부분 시작 ------------------%>
		<div class="mt-8 mb-14 w-full sm:mt-9">


			<%------------------ 프로필 & 신고 & 메뉴 시작 ------------------%>
			<div class="mb-8 flex flex-wrap sm:mb-9">
				<div class="flex items-center justify-center">

					<%--- 프로필 사진 ---%>
					<a href="/users/153479"> <img
						class="inline-block h-10 w-10 rounded-full"
						src="https://ssl.pstatic.net/static/pwe/address/img_profile.png"
						alt="프로필 사진">
					</a>
					<!-- 프로필 사진있고,없고 c:if 문으로 나누기 (위는 프로필사진이 없을때 기본프로필사진)-->



					<div class="ml-2 flex flex-1 flex-col text-base font-normal">

						<%--------- 아이디 부분 시작 ----------%>
						<a
							class="pl-0.5 text-gray-900 hover:text-blue-500 dark:text-gray-100 dark:hover:text-blue-200"
							href="/users/153479">fprhffj**** </a>
						<%--------- 아이디 부분 끝 ----------%>

						<%-- 활동점수,글작성시간,조회수부분 시작 --%>
						<div
							class="flex items-center gap-x-1 text-sm font-normal text-gray-700 dark:text-gray-300">
							<span> <svg class="inline h-3 w-3" width="9" height="12"
									viewBox="0 1 9 12" fill="none"
									xmlns="http://www.w3.org/2000/svg">
										<title>bolt icon</title>
										<path fill-rule="evenodd" clip-rule="evenodd"
										d="M5.21535 0.575533C5.4443 0.647704 5.6 0.86003 5.6 1.10009V3.85009L7.8 3.85009C8.00508 3.85009 8.19313 3.96419 8.28783 4.14609C8.38254 4.328 8.36818 4.54748 8.25057 4.71549L4.40057 10.2155C4.26291 10.4122 4.0136 10.4968 3.78464 10.4246C3.55569 10.3525 3.4 10.1401 3.4 9.90009L3.4 7.15009H1.2C0.994914 7.15009 0.806866 7.03599 0.712157 6.85408C0.617448 6.67218 0.631811 6.45269 0.749418 6.28468L4.59942 0.784684C4.73708 0.588021 4.98639 0.503362 5.21535 0.575533Z"
										fill="currentColor">
									    </path>
									</svg>10<%-- 활동점수 숫자 --%>
							</span> <span>·</span> <span>약 1시간 전<%-- 작성시간 --%></span> <span>·</span>
							<div class="flex items-center gap-x-0.5">
								<svg xmlns="http://www.w3.org/2000/svg" fill="none"
									viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor"
									aria-hidden="true" class="h-4 w-4 shrink-0">
										<path stroke-linecap="round" stroke-linejoin="round"
										d="M2.036 12.322a1.012 1.012 0 010-.639C3.423 7.51 7.36 4.5 12 4.5c4.638 0 8.573 3.007 9.963 7.178.07.207.07.431 0 .639C20.577 16.49 16.64 19.5 12 19.5c-4.638 0-8.573-3.007-9.963-7.178z"></path>
										<path stroke-linecap="round" stroke-linejoin="round"
										d="M15 12a3 3 0 11-6 0 3 3 0 016 0z">
										</path>
									</svg>
								68
								<%-- 조회수 숫자 --%>
							</div>

						</div>
						<%-- 활동점수,글작성시간,조회수부분 끝 --%>

					</div>
				</div>


				<%-- 버튼들 시작 --%>
				<div
					class="ml-auto flex items-center gap-x-4 text-sm text-gray-700 dark:text-gray-300 sm:gap-x-5">
					<div>
						<span class="sr-only">신고</span>
						<div class="relative" data-headlessui-state="">
							<div class="flex items-center">
								<button id="headlessui-menu-button-9" type="button"
									aria-haspopup="true" aria-expanded="false"
									data-headlessui-state="">
									<span class="sr-only">신고하기</span>
									<p>&#x1F6A8;</p>
									<!--  
										<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
											fill="currentColor" class="bi bi-emoji-angry-fill"
											viewBox="0 0 16 16">
  									    <path class="angry" stroke-linecap="round"
												stroke-linejoin="round"
												d="M8 16A8 8 0 1 0 8 0a8 8 0 0 0 0 16zM4.053 4.276a.5.5 0 0 1 .67-.223l2 1a.5.5 0 0 1 .166.76c.071.206.111.44.111.687C7 7.328 6.552 8 6 8s-1-.672-1-1.5c0-.408.109-.778.285-1.049l-1.009-.504a.5.5 0 0 1-.223-.67zm.232 8.157a.5.5 0 0 1-.183-.683A4.498 4.498 0 0 1 8 9.5a4.5 4.5 0 0 1 3.898 2.25.5.5 0 1 1-.866.5A3.498 3.498 0 0 0 8 10.5a3.498 3.498 0 0 0-3.032 1.75.5.5 0 0 1-.683.183zM10 8c-.552 0-1-.672-1-1.5 0-.247.04-.48.11-.686a.502.502 0 0 1 .166-.761l2-1a.5.5 0 1 1 .448.894l-1.009.504c.176.27.285.64.285 1.049 0 .828-.448 1.5-1 1.5z" />
										</svg>
										-->
								</button>
							</div>
						</div>
					</div>

					<!-- 스크랩 버튼 
						<button>
							<span class="sr-only">스크랩</span>
							<svg xmlns="http://www.w3.org/2000/svg" fill="none"
								viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor"
								aria-hidden="true"
								class="h-6 w-6 text-gray-400 hover:fill-current hover:text-blue-500 focus:outline-0 focus:ring-0 dark:hover:text-blue-200">
								<path stroke-linecap="round" stroke-linejoin="round"
									d="M17.593 3.322c1.1.128 1.907 1.077 1.907 2.185V21L12 17.25 4.5 21V5.507c0-1.108.806-2.057 1.907-2.185a48.507 48.507 0 0111.186 0z"></path></svg>
						</button>
						-->

					<span class="sr-only">더보기</span>
					<div class="relative" id="more_list" data-headlessui-state="open">
						<div class="flex items-center" id="list_icon">
							<button id="headlessui-menu-button-10" type="button"
								aria-haspopup="true" aria-expanded="true"
								data-headlessui-state="open" aria-controls="edit_delete_list">
								<span class="sr-only">작성자 메뉴</span>
								<svg xmlns="http://www.w3.org/2000/svg" fill="none"
									viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor"
									aria-hidden="true"
									class="h-6 w-6 text-gray-400 hover:text-blue-500 focus:outline-0 focus:ring-0 dark:hover:text-blue-200">
										<path stroke-linecap="round" stroke-linejoin="round"
										d="M6.75 12a.75.75 0 11-1.5 0 .75.75 0 011.5 0zM12.75 12a.75.75 0 11-1.5 0 .75.75 0 011.5 0zM18.75 12a.75.75 0 11-1.5 0 .75.75 0 011.5 0z"></path></svg>
							</button>
						</div>
						<div
							class="absolute right-0 mt-2 w-40 space-y-2 rounded-lg border border-gray-500/30 bg-white p-3 shadow-lg dark:border-gray-500/70 dark:bg-gray-800 transform opacity-100 scale-100"
							aria-labelledby="headlessui-menu-button-10" id="edit_delete_list"
							role="menu" tabindex="0" data-headlessui-state="open">
							<button style="padding-bottom: 10px;"
								class="text-gray-700 dark:text-gray-300 group flex items-center space-x-2 px-2"
								id="headlessui-menu-item-46" role="menuitem" tabindex="-1"
								data-headlessui-state="">
								<svg xmlns="http://www.w3.org/2000/svg" fill="none"
									viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor"
									aria-hidden="true" class="h-5 w-5">
										<path stroke-linecap="round" stroke-linejoin="round"
										d="M16.862 4.487l1.687-1.688a1.875 1.875 0 112.652 2.652L10.582 16.07a4.5 4.5 0 01-1.897 1.13L6 18l.8-2.685a4.5 4.5 0 011.13-1.897l8.932-8.931zm0 0L19.5 7.125M18 14v4.75A2.25 2.25 0 0115.75 21H5.25A2.25 2.25 0 013 18.75V8.25A2.25 2.25 0 015.25 6H10"></path></svg>
								<span class="font-medium">수정하기</span>
							</button>
							<button
								class="text-gray-700 dark:text-gray-300 group flex items-center space-x-2 px-2"
								id="headlessui-menu-item-47" role="menuitem" tabindex="-1"
								data-headlessui-state="">
								<svg xmlns="http://www.w3.org/2000/svg" fill="none"
									viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor"
									aria-hidden="true" class="h-5 w-5">
										<path stroke-linecap="round" stroke-linejoin="round"
										d="M14.74 9l-.346 9m-4.788 0L9.26 9m9.968-3.21c.342.052.682.107 1.022.166m-1.022-.165L18.16 19.673a2.25 2.25 0 01-2.244 2.077H8.084a2.25 2.25 0 01-2.244-2.077L4.772 5.79m14.456 0a48.108 48.108 0 00-3.478-.397m-12 .562c.34-.059.68-.114 1.022-.165m0 0a48.11 48.11 0 013.478-.397m7.5 0v-.916c0-1.18-.91-2.164-2.09-2.201a51.964 51.964 0 00-3.32 0c-1.18.037-2.09 1.022-2.09 2.201v.916m7.5 0a48.667 48.667 0 00-7.5 0"></path></svg>
								<span class="font-medium" data-toggle="modal"
									data-target="#myModal" data-dismiss="modal">삭제하기</span>
							</button>
						</div>
					</div>
				</div>
				<%-- 버튼들 끝 --%>

				<%-- 삭제하기 모달시작  --%>
				<div class="modal fade" id="myModal" role="dialog">
					<div class="modal-dialog modal-sm" style="margin-top: 250px;">
						<div class="modal-content">
						    
							
							<div class="modal-body" style="margin: 10px; display: flex;">
                             <p style="font-size:25px; line-height: 50px; margin-right: 20px;">&#127760;</p>
							<div style="display: block;">
								<h2 style="margin-bottom: 12px;">게시물 삭제</h2>
								<p style="color: #9ca3af;">정말로 삭제하시겠습니까?</p>
							</div>
							</div>
							<div class="modal-footer"
								style="border-top: none; background-color: #FAFAFA">
								<button type="button" class="inline-flex w-full justify-center rounded-md border border-transparent bg-blue-500 px-3 py-2 text-base font-medium text-white shadow-sm hover:bg-blue-400 focus:outline-none sm:ml-3 sm:w-auto sm:text-sm" style="background-color: #0090f9;">
								확인</button>
								
								<button type="button" data-dismiss="modal" class="inline-flex w-full justify-center rounded-md border border-gray-500/30 bg-white px-3 py-2 text-base font-medium shadow-sm hover:bg-gray-100 focus:outline-none dark:border-gray-500/70 dark:bg-gray-700 dark:hover:bg-gray-600 sm:ml-3 sm:mt-0 sm:w-auto sm:text-sm">
								취소</button>
							</div>
						</div>
					</div>
				</div>

				<%-- 삭제하기 모달 끝 --%>

			</div>
			<%------------------ 프로필 & 신고 & 메뉴 끝 ------------------%>


			<%---------------------------- 글제목 시작 ----------------------------%>
			<h1
				class="block break-all text-xl font-semibold leading-7 sm:text-3xl sm:leading-10">
				안녕하세요 선배님들 질문이 있습니다.</h1>
			<%---------------------------- 글제목 끝 ----------------------------%>


			<%---------------------------- 글 본문 시작 ----------------------------%>
			<div
				class="my-6 text-sm text-gray-700 dark:text-gray-300 sm:my-8 sm:text-base">
				<div class="remirror-theme">
					<div class="remirror-editor-wrapper">
						<div contenteditable="false" translate="no" role="textbox"
							aria-multiline="true" aria-readonly="true" aria-label=""
							aria-placeholder="내용을 입력해주세요."
							class="ProseMirror remirror-editor remirror-a11y-dark">
							<p column-span="none">회사에서 에딧플러스를 쓰는데...국비 다니기 전에 생활코딩 들으면서
								아톰은 써봤는데 이런 메모장 같은 에디터가 있는 줄은 몰랐어요.</p>
							<p column-span="none">
								<br class="ProseMirror-trailingBreak">
							</p>
							<p column-span="none">개인 공부할 땐 vscode쓰는데 쓸 때마다 숨통 트이는 느낌 나서
								좋네요ㅠ</p>
							<p column-span="none">
								<br class="ProseMirror-trailingBreak">
							</p>
							<p column-span="none">vscode는 제가 만들어둔 함수나 라이브러리를 쓸 때 에디터에서
								알아서 뭘 쓸지 서랍을 열어준다고 해야하나...암튼 그래서 훨씬 편하고 이해가 빠른데 에딧플러스는 뭐 걍 치는 대로
								다 쳐지니까 나침반 없이 사막 걷는 느낌이랄까요...</p>

							<p column-span="none">
								<br class="ProseMirror-trailingBreak">
							</p>
							<p column-span="none">vscode는 제가 만들어둔 함수나 라이브러리를 쓸 때 에디터에서
								알아서 뭘 쓸지 서랍을 열어준다고 해야하나...암튼 그래서 훨씬 편하고 이해가 빠른데 에딧플러스는 뭐 걍 치는 대로
								다 쳐지니까 나침반 없이 사막 걷는 느낌이랄까요...</p>
-
							<p column-span="none">
								<br class="ProseMirror-trailingBreak">
							</p>
							<p column-span="none">vscode는 제가 만들어둔 함수나 라이브러리를 쓸 때 에디터에서
								알아서 뭘 쓸지 서랍을 열어준다고 해야하나...암튼 그래서 훨씬 편하고 이해가 빠른데 에딧플러스는 뭐 걍 치는 대로
								다 쳐지니까 나침반 없이 사막 걷는 느낌이랄까요...</p>
						</div>
					</div>
				</div>
			</div>
			<%---------------------------- 글 본문 끝 ---------------------------%>

			<br> <br>


			<%-------------- 해시태그, 추천 시작 --------------%>
			<div class="flex items-center space-x-1">

				<%-- 해시태그 시작 --%>
				<div class="flex flex-1 flex-wrap items-center"
					style="display: contents;">
					<a
						class="my-0.5 mx-1.5 flex h-7 items-center rounded-[39px] bg-gray-100 px-3 py-1.5 text-sm font-normal text-gray-700 hover:text-blue-500 hover:no-underline dark:bg-gray-700 dark:text-gray-300 dark:hover:text-blue-200"
						href="/articles/tagged/%ED%9A%8C%EC%82%AC"
						style="border-radius: 39px; background-color: #e5e7eb;"> <span
						class="hover:no-underline">#회사</span>
					</a>
				</div>

				<div class="flex flex-1 flex-wrap items-center"
					style="display: contents;">
					<a
						class="my-0.5 mx-1.5 flex h-7 items-center rounded-[39px] bg-gray-100 px-3 py-1.5 text-sm font-normal text-gray-700 hover:text-blue-500 hover:no-underline dark:bg-gray-700 dark:text-gray-300 dark:hover:text-blue-200"
						href="/articles/tagged/%ED%9A%8C%EC%82%AC"
						style="border-radius: 39px; background-color: #e5e7eb;"> <span
						class="hover:no-underline">#회사</span>
					</a>
				</div>
				<%-- 해시태그 끝 --%>

				<%-- 좋아요 시작 --%>
				<div class="like_article" style="margin-left: auto;">
					<div data-sid="CAFE" data-did="CAFE"
						data-cid="11262350_dieselmania_39919026" data-catgid="11262350"
						class="ReactionLikeIt u_likeit_list_module _cafeReactionModule"
						data-loaded="1" data-facetype="0">
						<a href="#" data-ishiddenlabel="false" role="button"
							data-type="like" title="이 글 좋아요 클릭" style="color: black;"
							class="like_no u_likeit_list_btn _button on" aria-pressed="true">
							<span class="u_ico _icon" id="like_button"
							style="display: flex; text-align: center; margin-right: 50px;">
								<svg style="margin-top: 3px; margin-right: 5px;"
									xmlns="http://www.w3.org/2000/svg" width="16" height="16"
									fill="currentColor" class="bi bi-heart" viewBox="0 0 16 16">
                                      <path
										d="m8 2.748-.717-.737C5.6.281 2.514.878 1.4 3.053c-.523 1.023-.641 2.5.314 4.385.92 1.815 2.834 3.989 6.286 6.357 3.452-2.368 5.365-4.542 6.286-6.357.955-1.886.838-3.362.314-4.385C13.486.878 10.4.28 8.717 2.01L8 2.748zM8 15C-7.333 4.868 3.279-3.04 7.824 1.143c.06.055.119.112.176.171a3.12 3.12 0 0 1 .176-.17C12.72-3.042 23.333 4.867 8 15z" />
								  </svg> <span class="u_txt _label"
								style="line-height: 23px; margin-left: 3px;">좋아요</span> <span
								class="u_txt _count"
								style="font-style: normal; line-height: 23px; margin-left: 6px;">2</span>
						</span>
						</a>
					</div>

				</div>
				<%-- 좋아요 끝 --%>

			</div>
			<%-------------- 해시태그, 추천 끝 --------------%>

			<br> <br>

			<div style="background-color: #FAFAFA; border-radius: 10px;">
				<div style="padding: 10px; border-radius: 15px;">
					<div style="margin-bottom: 1%; display: flex;">
						<a>이전글</a>
						<div
							style="border-right: 1px solid black; margin-left: 7px; height: 17px; margin-top: 4px;"></div>
						&nbsp;&nbsp;<a href="#" id="before_write"
							onclick="javascript:location.href='view_2.action?seq=218&amp;searchType=subject&amp;searchWord=&amp;gobackURL=/list.action'">
							좋으니 사랑해서 사랑을 시작할때 니가 얼마나 예쁜지 모르지</a>
					</div>

					<div style="margin-top: 10px; display: flex;">
						<a>다음글</a>
						<div
							style="border-right: 1px solid black; margin-left: 7px; height: 17px; margin-top: 4px;"></div>
						&nbsp;&nbsp;<a href="#" id="before_write"
							onclick="javascript:location.href='view_2.action?seq=218&amp;searchType=subject&amp;searchWord=&amp;gobackURL=/list.action'">
							그모습을 아직도 못잊어 헤어나오지못해 니소식 들린 날은 더</a>
					</div>

				</div>

			</div>

		</div>
		<%------------------ 글 전체몸통 부분  끝 ------------------%>


		<!--  광고 반응형 관련 체크 !!
			<div class="space-y-2 lg:hidden">
				<div class="hidden items-center lg:flex">
					<a class="min-w-0 flex-1 sm:flex" href="/banners/612"
						target="_blank"><img class="max-h-[380px] w-full"
						src="//file.okky.kr/banner/1667982468416.png" alt=""></a>
				</div>
				<a class="flex items-center justify-center lg:hidden"
					href="/banners/612" target="_blank"><img
					class="min-w-0 max-w-[640px]"
					src="//file.okky.kr/banner/1667982468418.png" alt=""></a>
				<div class="hidden items-center lg:flex" id="">
					<a class="min-w-0 flex-1 sm:flex" href="/banners/600"
						target="_blank"><img class="h-[140px] w-full"
						src="//file.okky.kr/banner/1666946796655.png" alt=""></a>
				</div>
				<a class="flex items-center justify-center lg:hidden"
					href="/banners/600" target="_blank"><img
					class="min-w-0 max-w-[640px]"
					src="//file.okky.kr/banner/1666946796657.png" alt=""></a>			
			</div>
			-->


		<!-------------------------------------- 광고부분 시작 -------------------------------------->
		<div class="hidden lg:block">
			<div class="hidden items-center lg:flex">
				<a class="min-w-0 flex-1 sm:flex" href="/banners/600"
					target="_blank"><img class="h-[140px] w-full"
					src="//file.okky.kr/banner/1666946796655.png" alt=""></a>
			</div>
		</div>
		<!-------------------------------------- 광고부분 끝 -------------------------------------->


		<!---------------------------------------- 댓글 시작 ---------------------------------------->
		<div class="mt-8">
			<section aria-labelledby="notes-title">
				<div class="relative mb-5 mt-8">
					<div class="absolute inset-0 flex items-center" aria-hidden="true">
						<div
							class="w-full border-t border-gray-500/30 dark:border-gray-500/70"></div>
					</div>
					<div class="relative flex justify-center">
						<span class="hidden bg-white px-2 text-sm text-gray-500">Continue</span>
					</div>
				</div>
				<div class="mb-6 sm:mb-8">
					<h2 id="notes-title">1개의 댓글</h2>
				</div>
				<div
					class="my-4 rounded-lg border border-gray-500/30 p-4 dark:border-gray-500/70 sm:my-10 sm:p-6">
					<div class="flex gap-x-2 sm:gap-x-4">
						<div class="shrink-0">
							<img class="h-6 w-6 rounded-full sm:h-10 sm:w-10"
								src="https://ssl.pstatic.net/static/pwe/address/img_profile.png"
								alt="프로필 사진">
						</div>
						<div class="min-w-0 flex-1">
							<form action="#">
								<tr>
					<th style="width: 15%; background-color: #DDDDDD;">내용</th>
					<td>
						<textarea style="width: 100%; height: 200px;" name="content" id="content"></textarea>
					</td>
				</tr>
								<div class="mt-3 flex items-center justify-end gap-x-4">
									<button type="submit"
										class="inline-flex items-center space-x-2 rounded-md bg-blue-500 px-8 py-2 text-sm font-semibold leading-6 text-white shadow-sm hover:bg-blue-600 disabled:bg-blue-500 disabled:opacity-40" style="background-color: #0090f9">
										댓글 쓰기</button>
								</div>
							</form>
						</div>
					</div>
				</div>
				<div class="my-8">
					<ul class="divide-y divide-gray-500/30 dark:divide-gray-500/70">
						<li id="note-1570712" class="py-6"><div
								class="flex items-center space-x-2">
								<div class="flex-shrink-0">
									<a href="/users/110882"><img
										class="h-8 w-8 rounded-full sm:h-12 sm:w-12"
										src="https://ssl.pstatic.net/static/pwe/address/img_profile.png"
										alt="프로필 사진"></a>
								</div>
								<div class="flex flex-1 flex-col text-sm font-medium" style="margin-left: 10px;">
									<a
										class="w-fit pl-0.5 text-gray-900 hover:text-blue-500 dark:text-gray-100 dark:hover:text-blue-200"
										href="/users/110882">쿠잉</a>
									<div
										class="flex items-center space-x-1 text-xs font-normal text-gray-700 dark:text-gray-300">
										<span><svg class="inline h-3 w-3" width="9" height="12"
												viewBox="0 1 9 12" fill="none"
												xmlns="http://www.w3.org/2000/svg">
													<title>bolt icon</title><path fill-rule="evenodd"
													clip-rule="evenodd"
													d="M5.21535 0.575533C5.4443 0.647704 5.6 0.86003 5.6 1.10009V3.85009L7.8 3.85009C8.00508 3.85009 8.19313 3.96419 8.28783 4.14609C8.38254 4.328 8.36818 4.54748 8.25057 4.71549L4.40057 10.2155C4.26291 10.4122 4.0136 10.4968 3.78464 10.4246C3.55569 10.3525 3.4 10.1401 3.4 9.90009L3.4 7.15009H1.2C0.994914 7.15009 0.806866 7.03599 0.712157 6.85408C0.617448 6.67218 0.631811 6.45269 0.749418 6.28468L4.59942 0.784684C4.73708 0.588021 4.98639 0.503362 5.21535 0.575533Z"
													fill="currentColor"></path></svg>6.7k</span><span>·</span><a
											class="text-gray-700 hover:text-blue-500 dark:text-gray-300 dark:hover:text-blue-200"
											href="/articles/1352864#note-1570712">약 1시간 전</a>
									</div>
								</div>


								<%-- 좋아요 시작 --%>
								<div class="like_article" style="margin-left: auto;">
									<div data-sid="CAFE" data-did="CAFE"
										data-cid="11262350_dieselmania_39919026"
										data-catgid="11262350"
										class="ReactionLikeIt u_likeit_list_module _cafeReactionModule"
										data-loaded="1" data-facetype="0">
										<a href="#" data-ishiddenlabel="false" role="button"
											data-type="like" title="이 글 좋아요 클릭" style="color: black;"
											class="like_no u_likeit_list_btn _button on"
											aria-pressed="true"> <span class="u_ico _icon"
											style="display: flex; text-align: center; margin-right: 50px;">
												<svg style="margin-top: 3px; margin-right: 5px;"
													xmlns="http://www.w3.org/2000/svg" width="16" height="16"
													fill="currentColor" class="bi bi-heart" viewBox="0 0 16 16">
				                                      <path
														d="m8 2.748-.717-.737C5.6.281 2.514.878 1.4 3.053c-.523 1.023-.641 2.5.314 4.385.92 1.815 2.834 3.989 6.286 6.357 3.452-2.368 5.365-4.542 6.286-6.357.955-1.886.838-3.362.314-4.385C13.486.878 10.4.28 8.717 2.01L8 2.748zM8 15C-7.333 4.868 3.279-3.04 7.824 1.143c.06.055.119.112.176.171a3.12 3.12 0 0 1 .176-.17C12.72-3.042 23.333 4.867 8 15z" />
												  </svg> <span class="u_txt _label"
												style="line-height: 23px; margin-left: 3px;">좋아요</span> <span
												class="u_txt _count"
												style="font-style: normal; line-height: 23px; margin-left: 6px;">2</span>
										</span>
										</a>
									</div>

								</div>
								<%-- 좋아요 끝 --%>


							</div>
							<div class="flex">
								<div style="padding-top: 20px;"
									class="remirror-editor mb-2 flex-1 break-all text-sm leading-relaxed text-gray-700 dark:text-gray-300">
									<div class="remirror-theme">
										<div class="remirror-editor-wrapper">
											<div contenteditable="false" translate="no" role="textbox"
												aria-multiline="true" aria-readonly="true" aria-label=""
												aria-placeholder="내용을 입력해주세요."
												class="ProseMirror remirror-editor remirror-a11y-dark">
												<p column-span="none">그냥 vscode 쓰시면 되죠</p>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div>
								<div class="flex space-x-4">
									<button id="comment_comment"
										class="text-xs font-normal text-gray-500 hover:text-blue-500 hover:no-underline focus:outline-none dark:text-gray-400 dark:hover:text-blue-200">댓글
										쓰기</button>
								</div>
								
								</br>

								<div id="comment_comment_window" 
					class="my-4 rounded-lg  border-gray-500/30 p-4 dark:border-gray-500/70 sm:my-10 sm:p-6">
					<div class="flex gap-x-2 sm:gap-x-4" id="dd">
						<div class="shrink-0">
							<img class="h-6 w-6 rounded-full sm:h-10 sm:w-10"
								src="https://ssl.pstatic.net/static/pwe/address/img_profile.png"
								alt="프로필 사진">
						</div>
						<div class="min-w-0 flex-1">
							<form action="#">
								<tr>
					<th style="width: 15%; background-color: #DDDDDD;">내용</th>
					<td>
						<textarea style="width: 100%; height: 200px;" name="comment" id="comment"></textarea>
					</td>
				</tr>
								<div class="mt-3 flex items-center justify-end gap-x-4">
									<button type="submit"
										class="inline-flex items-center space-x-2 rounded-md bg-blue-500 px-8 py-2 text-sm font-semibold leading-6 text-white shadow-sm hover:bg-blue-600 disabled:bg-blue-500 disabled:opacity-40" style="background-color: #0090f9">
										댓글 쓰기</button>
								</div>
							</form>
						</div>
					</div>
				</div>
								
								
								<div class="ml-2.5 border-l-2 pl-4">
									<section class="mt-5 text-sm"
										id="headlessui-disclosure-panel-12" data-headlessui-state="">
										<ul
											class="divide-y divide-dashed divide-gray-500/30 dark:divide-gray-500/70"></ul>
									</section>
								</div>
							</div></li>
					</ul>
				</div>
			</section>
		</div>
		<!---------------------------------------- 댓글 끝 ---------------------------------------->


	</div>


	<div class="mt-7 space-y-2 lg:hidden">
		<div class="hidden lg:flex">
			<ins class="adsbygoogle" data-adtest="off"
				data-ad-client="ca-pub-8103607814406874" data-ad-slot="6573675943"
				style="display: inline-block; width: 180px; height: 0px;"
				data-adsbygoogle-status="done" data-ad-status="unfilled">
				<div id="aswift_7_host" tabindex="0" title="Advertisement"
					aria-label="Advertisement"
					style="border: none; height: 0px; width: 180px; margin: 0px; padding: 0px; position: relative; visibility: visible; background-color: transparent; display: inline-block; overflow: hidden; opacity: 0;">
					<iframe id="aswift_7" name="aswift_7"
						style="left: 0; position: absolute; top: 0; border: 0; width: 180px; height: 380px;"
						sandbox="allow-forms allow-popups allow-popups-to-escape-sandbox allow-same-origin allow-scripts allow-top-navigation-by-user-activation"
						width="180" height="380" frameborder="0" marginwidth="0"
						marginheight="0" vspace="0" hspace="0" allowtransparency="true"
						scrolling="no"
						src="https://googleads.g.doubleclick.net/pagead/ads?client=ca-pub-8103607814406874&amp;output=html&amp;h=380&amp;slotname=6573675943&amp;adk=1341480177&amp;adf=1414739156&amp;pi=t.ma~as.6573675943&amp;w=180&amp;lmt=1668429126&amp;format=180x380&amp;url=https%3A%2F%2Fokky.kr%2Farticles%2F1352864&amp;adtest=off&amp;wgl=1&amp;adsid=ChAIgOvHmwYQ4OrhnIqtvdggEj0AxSHseYucKbnDEF8aWgsWrFmtvxXm6QVqaYLkaIJOTwxKY1k3opFKa82Ogo6fnYaQGO7h9Ymwolbeplrm&amp;uach=WyJXaW5kb3dzIiwiMTAuMC4wIiwieDg2IiwiIiwiMTA3LjAuNTMwNC4xMDciLFtdLGZhbHNlLG51bGwsIjY0IixbWyJHb29nbGUgQ2hyb21lIiwiMTA3LjAuNTMwNC4xMDciXSxbIkNocm9taXVtIiwiMTA3LjAuNTMwNC4xMDciXSxbIk5vdD1BP0JyYW5kIiwiMjQuMC4wLjAiXV0sZmFsc2Vd&amp;dt=1668429125828&amp;bpp=1&amp;bdt=168&amp;idt=176&amp;shv=r20221109&amp;mjsv=m202211090101&amp;ptt=9&amp;saldr=aa&amp;abxe=1&amp;cookie=ID%3Dc2e6bc1a7f9f8397-22ffce02b8d30068%3AT%3D1654151513%3ART%3D1654151513%3AS%3DALNI_MbVaXKl4A9HhNyYMnadfuNhO0ltDA&amp;gpic=UID%3D0000087e7f3a434c%3AT%3D1665214590%3ART%3D1668411893%3AS%3DALNI_MbSnsZD3OHO7mUnNvWhDtqBUrMYWg&amp;prev_fmts=0x0&amp;nras=1&amp;correlator=148367628956&amp;frm=20&amp;pv=1&amp;ga_vid=472087600.1654151510&amp;ga_sid=1668429126&amp;ga_hid=1952762818&amp;ga_fc=1&amp;u_tz=540&amp;u_his=20&amp;u_h=864&amp;u_w=1536&amp;u_ah=824&amp;u_aw=1536&amp;u_cd=24&amp;u_sd=1.25&amp;dmc=8&amp;adx=-12245933&amp;ady=-12245933&amp;biw=1519&amp;bih=722&amp;scr_x=0&amp;scr_y=0&amp;eid=44759876%2C44759927%2C44759842%2C44761793%2C42531706%2C44778780%2C31070831%2C44770880%2C44777812&amp;oid=2&amp;pvsid=4416387770424939&amp;tmod=1501809658&amp;uas=0&amp;nvt=2&amp;eae=0&amp;fc=1920&amp;brdim=0%2C0%2C0%2C0%2C1536%2C0%2C1536%2C824%2C1536%2C722&amp;vis=1&amp;rsz=%7C%7CenEr%7C&amp;abl=CS&amp;pfx=0&amp;fu=32768&amp;bc=31&amp;ifi=8&amp;uci=a!8&amp;fsb=1&amp;xpc=ozdVJuDN6e&amp;p=https%3A//okky.kr&amp;dtd=180"
						data-google-container-id="a!8"
						data-google-query-id="CIuhyuvWrfsCFW2dwgod5V4LaQ"
						data-load-complete="true"></iframe>
				</div>
			</ins>
		</div>
		<div class="hidden items-center lg:flex">
			<a class="min-w-0 flex-1 sm:flex" href="/banners/603" target="_blank"><img
				class="w-full" src="//file.okky.kr/banner/1667440200249.jpg" alt=""></a>
		</div>
		<a class="flex items-center justify-center lg:hidden"
			href="/banners/603" target="_blank"><img
			class="min-w-0 max-w-[640px]"
			src="//file.okky.kr/banner/1667440200251.jpg" alt=""></a>
	</div>
</div>
<%---------------------------------- 컨테이너 끝 ----------------------------------%>




