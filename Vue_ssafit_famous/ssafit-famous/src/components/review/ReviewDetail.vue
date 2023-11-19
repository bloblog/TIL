<!-- 리뷰 상세 보기 -->
<template>
    <div>
        <h2>리뷰 상세 보기</h2>
        <div>
            <h3>제목 : 최고의 스터디 짱짱</h3>
            <p>작성자 : 최승준 작성일 : 2023.11.18 스터디명 : 최승준과 아이들 카테고리 : 운동</p>
            <p>우리 스터디 짱이야 최고야 어쩌구 저쩌구</p>
        </div>
        <button @click="move">닫기</button>
    </div>
</template>

<script setup>
import {ref} from 'vue';
import {useRouter, useRoute} from 'vue-router';

const router = useRouter();
const route = useRoute();

const move = function() {
    router.go(-1);
}

</script>

<style scoped>

</style>