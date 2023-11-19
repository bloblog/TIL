<!-- 스터디 디테일 들어가면 상단에 보이는 기본 정보들 -->
<template>
    <div>
        <h3>스터디명 : 빛나는 코드 마법사들</h3>
        <p># 코딩</p>
        <p>
            팀원 : 
            <img src="#">
            <img src="#">
            <img src="#">
            <img src="#">
        </p>
        <p>시작일 2023.09.31</p>
        <p>종료일 2023.12.31</p>
        <button @click="modifyForm">수정하기</button>
        <div v-if="modify">
            <h4>스터디 정보 수정 모달</h4>
            <CreateFormItem/>
            <SearchMemberItem/>
            <button @click="modifyForm">취소</button>
            <!--- 수정 반영 처리 필요 -->
            <button @click="modifyForm">수정</button> 
        </div>
    </div>
</template>

<script setup>
import SearchMemberItem from '../user/SearchMemberItem.vue';
import CreateFormItem from './CreateFormItem.vue';
import { ref } from 'vue';

const modify = ref(false);
const modifyForm = ref(function(){
    modify.value = !modify.value;
})
</script>

<style scoped>

</style>