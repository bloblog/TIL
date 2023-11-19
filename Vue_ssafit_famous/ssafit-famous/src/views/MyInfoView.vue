<!-- 회원정보 페이지 -->
<template>
    <div>
        <h2>회원정보 페이지 입니다.</h2>
        <MenuNav/>
        <router-view/>
    </div>
</template>

<script setup>
import MenuNav from '../components/user/MenuNav.vue';
</script>

<style scoped>

</style>