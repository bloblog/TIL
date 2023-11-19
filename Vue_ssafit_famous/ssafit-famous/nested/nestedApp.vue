<template>
    <div class="background">
        <header>
            <HeaderBar/>
        </header>
        
        <RouterView></RouterView>
        
    </div>
</template>

<script setup>
import HeaderBar from '../src/components/common/HeaderBar.vue';

</script>

<style scoped>
.background {
    background-image: url('@/assets/images/background.jpg');
    background-position: left top;
}

header {
    height: 15vw;
}
</style>