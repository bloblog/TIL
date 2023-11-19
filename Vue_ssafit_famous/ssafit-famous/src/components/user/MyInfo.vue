<!-- 사용자 정보 수정 -->
<template>
    <div>
        <h3>사용자정보수정</h3>
        <form>
            <!-- 아이디(변경 불가), 현재 비밀번호, 새 비밀번호, 새 비밀번호 확인 -->
            <label for="id">아이디</label>
            <input type="text" name="id">
            <br/>
            <label for="oldPw">현재 비밀번호</label>
            <input type="text" name="oldPw" required/>
            <br/>
            <label for="newPw">새 비밀번호</label>
            <input type="text" name="newPw" required/>
            <br/>
            <label for="newPwConfirm">새 비밀번호 확인</label>
            <input type="text" name="newPwConfirm" required/>
            <br/>
            <input type="submit" value="회원정보 수정">
        </form>
    </div>
</template>

<script setup>

</script>

<style scoped>
    
</style>