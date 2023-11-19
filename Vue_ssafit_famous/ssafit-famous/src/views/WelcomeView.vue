<template>
    <div>
        <button @click="loginForm">로그인</button>
        <button @click="registForm">회원가입</button>
        <a href="/nested/index.html">그냥 둘러볼래</a>
        <div class="modals">
            <div class="black-bg" v-if="loginModal == true">
                <div class="white-bg">
                    <h4>로그인모달</h4>
                    <p>아이디 비번</p>
                    <button @click="loginForm">닫기</button>
                </div>
            </div>
            <div class="black-bg" v-if="registModal == true">
                <div class="white-bg">
                    <h4>회원가입모달</h4>
                    <p>아이디 비번</p>
                    <button @click="registForm">닫기</button>
                </div>
            </div>
            <div>
                <h1>대문페이지입니다.</h1>
                <div>
                    
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>

import { ref } from 'vue';

const loginModal = ref(false);
const registModal = ref(false);

const loginForm = ref(function() {
    loginModal.value = !loginModal.value;
})

const registForm = ref(function() {
    registModal.value = !registModal.value;
})

</script>

<style scoped>
.modals {
  box-sizing: border-box;
}
.black-bg {
  width: 100%; height:100%;
  background: rgba(0,0,0,0.5);
  position: fixed; 
  padding: 50px;
}
.white-bg {
  width: 50%; background: white;
  border-radius: 8px;
  padding: 20px;
} 
</style>
