<template>
    <div>
        <div class="container">
            <div class="row g-2">
                <div class="col-md-10">
                    <div class="row g-2">
                        <div class="col-md-4">
                            <select class="form-select border-0 py-3" v-model="key">
                                <option disabled>정렬기준</option>
                                <option value="viewCnt">조회수</option>
                                <option value="reviewDate">최신순</option>
                            </select>
                        </div>
                        <div class="col-md-8">
                            <input type="text" v-model="word" class="form-control border-0 py-3" placeholder="회고를 검색하세요.">
                        </div>
                        
                    </div>
                </div>
                <div class="col-md-2">
                    <button class="btn btn-dark border-0 w-100 py-3" style="background-color: #0DC5B8; border-radius: 30px;">검색</button>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import {ref} from 'vue';

const key= ref("정렬기준");
const word = ref(null);

</script>

<style scoped>

</style>