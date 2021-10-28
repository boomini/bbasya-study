<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp"%>

<!--Container -->
<div class="mx-auto bg-grey-400">
	<!--Screen-->
	<div class="min-h-screen flex flex-col">
		<!--Header Section Starts Here-->
		<header class="bg-nav">
			<div class="flex justify-between">
				<div class="p-1 mx-3 inline-flex items-center">
					<h1 class="text-white p-2">SSAFY</h1>
				</div>
				<div class="p-1 flex flex-row items-center">
					<a href=""
						class="text-white p-2 mr-2 no-underline hidden md:block lg:block">Sign
						out</a>
					<div id="ProfileDropDown"
						class="rounded hidden shadow-md bg-white absolute pin-t mt-12 mr-1 pin-r">
						<ul class="list-reset">
							<li><a href="#"
								class="no-underline px-4 py-2 block text-black hover:bg-grey-light">My
									account</a></li>
							<li><a href="#"
								class="no-underline px-4 py-2 block text-black hover:bg-grey-light">Notifications</a>
							</li>
							<li>
								<hr class="border-t mx-2 border-grey-ligght">
							</li>
							<li><a href="#"
								class="no-underline px-4 py-2 block text-black hover:bg-grey-light">Logout</a>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</header>
		<!--/Header-->

		<div class="flex flex-1">
			<!--Sidebar-->
			<aside id="sidebar"
				class="bg-side-nav w-1/2 md:w-1/6 lg:w-1/6 border-r border-side-nav hidden md:block lg:block">

				<ul class="list-reset flex flex-col">
					<li
						class=" w-full h-full py-3 px-2 border-b border-light-border bg-white">
						<a href="${root}"
						class="font-sans font-hairline hover:font-normal text-sm text-nav-item no-underline">
							Main </a>
					</li>
					<li class="w-full h-full py-3 px-2 border-b border-light-border">
						<a href="${root}/list"
						class="font-sans font-hairline hover:font-normal text-sm text-nav-item no-underline">
							상품리스트 </a>
					</li>
					<li class="w-full h-full py-3 px-2 border-b border-light-border">
						<a href="${root}/register"
						class="font-sans font-hairline hover:font-normal text-sm text-nav-item no-underline">
							상품등록 </a>
					</li>
				</ul>

			</aside>
			<!--/Sidebar-->

		</div>
		<footer class="bg-grey-darkest text-white p-2">
			<div class="flex flex-1 mx-auto">SSAFY Spring</div>
			<div class="flex flex-1 mx-auto">Contacts 어쩌구</div>
		</footer>
	</div>

</div>
</body>

</html>