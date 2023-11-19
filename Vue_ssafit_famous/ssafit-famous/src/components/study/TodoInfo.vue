<!-- 할일 쭈르륵 + 체크박스 + 스터디장 클릭스 수정가능 -->
<template>
    <div>
        <h3>Todo</h3>
        <ol>
            <li>
                <p>2023.11.18</p>
                <a href="#">알고리즘 1문제 풀기</a>
                <input type="checkbox">
            </li>
            <li>
                <p>2023.11.20</p>
                <a href="#">코드 리뷰</a>
                <input type="checkbox">
            </li>
            <li>
                <p>2023.12.01</p>
                <a href="#">푼 문제 깃에 올리기</a>
                <input type="checkbox">
            </li>
        </ol>
        <button @click="addForm">추가</button>
        <div v-if="add">
            <h4>todo 추가 모달</h4>
            <form>
                <label for="todoContent">내용</label>
                <input type="text"><br/>
                <label for="todoStart">시작일</label>
                <input type="text"><br/>
                
                <label for="todoEnd">마감일</label>
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