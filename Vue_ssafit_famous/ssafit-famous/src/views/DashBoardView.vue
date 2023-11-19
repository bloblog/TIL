<template>
    <div>
        <h2>대시보드</h2>
        <CalendarItem/>
        <TodoProcess/>
        <ExpRank/>
    </div>
</template>

<script setup>
import CalendarItem from '../components/study/CalendarItem.vue';
import TodoProcess from '../components/study/TodoProcess.vue'
import ExpRank from '../components/user/ExpRank.vue';
</script>

<style scoped>

</style>