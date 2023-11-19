<template>
    <div>
        <SearchBar/>

        <!-- Property List Start -->
        <div class="container-xxl py-2">
            <div class="container">
                <p>카테고리 클릭시 게시판으로 이동합니다 ^.^</p>
                <ul class="nav nav-pills d-inline-flex justify-content-end mb-3">
                    <li class="nav-item me-2">
                        <router-link to="/reviewDetailView" @click="select" class="btn btn-outline-primary">#코딩</router-link>
                    </li>
                    <li class="nav-item me-2">
                        <router-link to="/reviewDetailView" @click="select" class="btn btn-outline-primary">#운동</router-link>
                    </li>
                    <li class="nav-item me-2">
                        <router-link to="/reviewDetailView" @click="select" class="btn btn-outline-primary">#취미</router-link>
                    </li>
                    <li class="nav-item me-2">
                        <router-link to="/reviewDetailView" @click="select" class="btn btn-outline-primary">#독서</router-link>
                    </li>
                </ul>
                
            </div>
        </div>
        <!-- Property List End -->
        
        <TopReview/>
        
    </div>
</template>

<script setup>
import {ref} from 'vue';
import SearchBar from './SearchBar.vue';
import TopReview from './TopReview.vue';

const selectedCat = ref(null);

const select = function(event) {
    selectedCat.value = event.target.innerText.split("#")[1].trim();
}

</script>

<style scoped>

</style>