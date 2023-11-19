<!-- 캘린더 + 클릭시 수정 가능 (스터디장) -->
<template>
    <div>
        <h3>일정</h3>
        <!-- 캘린더 형태로 일정 표기-->
        <button @click="addForm">추가</button>
        <div v-if="add">
            <h4>일정 추가 모달</h4>
            <form>
                <label for="scheduleDate">날짜</label>
                <input type="text"><br/>
                <label for="scheduleContent">내용</label>
                <input type="text"><br/>
                <label for="schedulePlace">장소</label>
                <input type="text"><br/>
            </form>
            <button @click="addForm">취소</button>
            <!--- 추가 반영 처리 필요 -->
            <button @click="addForm">추가</button> 
        </div>
    </div>
</template>

<script setup>
import { ref } from 'vue';

const add = ref(false);
const addForm = ref(function(){
    add.value = !add.value;
})
</script>

<style scoped>

</style>