<!-- 회고 작성하기 -->
<template>
    <div>
        <h2>회고 작성하기</h2>
        <form>
            <div>
                <!-- 아래 3개는 값 고정 -->
                <label for="studyName">스터디명</label>
                <input type="text" name="studyName"><br/>
                <label for="studyCategory">카테고리</label>
                <input type="text" name="studyCategory"><br/>
                <label for="reviewDate">작성일</label>
                <input type="text" name="reviewDate"><br/>
                <label for="reviewImg">대표이미지</label>
                <input type="file" name="reviewImg"/>
                <!-- 파일 인풋 어떻게 하더라 -->
            </div>
            <div>
                <label for="reviewTitle">제목</label>
                <input type="text" name="reviewTitle"><br/>
                <label for="reviewContent">내용</label>
                <input type="textarea" name="reviewContent">
            </div>
            <input type="submit" value="작성완료">
        </form>
        <button @click="move">취소</button>
    </div>
</template>

<script setup>
import {useRouter, useRoute} from 'vue-router';

const router = useRouter();
const route = useRoute();

const move = function() {
    router.go(-1);
}
</script>

<style scoped>

</style>