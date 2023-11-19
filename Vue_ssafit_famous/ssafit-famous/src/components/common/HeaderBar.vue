<template>
    <div>
        <div class="container-fluid nav-bar">
            <nav id="header-container" class="navbar navbar-expand-lg navbar-light">
                <div>
                    <router-link to="/createStudy" class="nav-item nav-link">스터디 만들기</router-link>
                </div>
                <div>
                    <router-link to="/mainHome" id="logo" class="navbar-brand d-flex align-items-center text-center">
                        <img class="img-fluid" src="@/assets/images/logo.png" alt="Icon">
                    </router-link>
                </div>
                <div>
                    <button type="button" class="navbar-toggler" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarCollapse">
                    <div class="navbar-nav ms-auto">
                        <div class="nav-item dropdown">
                            <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">마이페이지</a>
                            <div class="dropdown-menu rounded-0 m-0">
                                <router-link to="/dashboard" class="dropdown-item">대시보드</router-link>
                                <router-link to="/infoView" class="dropdown-item">정보수정</router-link>
                            </div>
                        </div>
                    </div>
                    <a href="#" class="nav-item nav-link" v-if="loginUser">로그아웃</a>
                    <a href="#" class="nav-item nav-link" v-if="!loginUser">로그인</a>
                    <a href="#" class="nav-item nav-link" v-if="!loginUser">회원가입</a>
                </div>
                </div>
                
            </nav>
        </div>

    </div>
</template>

<script setup>
import {ref} from 'vue';

const loginUser = ref("soyi");
</script>

<style scoped>
#header-container {
    height: 100%;
    padding-right:5vw;
    padding-left: 8vw;
    background-size: 100%;
    background-position: center bottom;
    background-image: url('@/assets/images/header-background.png');
    background-repeat: no-repeat;
}

.navbar-expand-lg {
    height: 9vw;
    justify-content: space-evenly;

}

.img-fluid {
    width: 25vw;
}


</style>
