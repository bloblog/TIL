<template>
    <div>
        <div class="container-global" data-wow-delay="0.1s" style="margin-right: 10px; margin-left: 10px;">
            <h2>스터디 생성 폼</h2>
            <div class="create-form">
                <CreateFormItem/>
                <div class="v-line"></div>
                <div class="form-member">
                    <label>팀원 목록</label>
                    <SearchMemberItem/>
                </div>
            </div>
            <div class="col=12" style="text-align: center;">
                <button @click="exit" class="btn btn-secondary m-1" >취소</button>
                <button @click="create" class="btn btn-primary m-1">생성</button>
            
            </div>
        </div>
        
        
    </div>
</template>

<script setup>
import SearchMemberItem from '@/components/user/SearchMemberItem.vue'
import CreateFormItem from '../components/study/CreateFormItem.vue';
import {useRouter, useRoute} from 'vue-router';

const router = useRouter();
const route = useRoute();

const exit = function() {
    router.go(-1);
}

const create = function() {
    // api 보내고, api 반환값을 해당 스터디 번호로 해야 함
    const studyKey = 0; // 일단 0으로 해두자
    router.push("/studyDetail");
}

</script>

<style scoped>
.container-global {
    background-color: #fffae49f;
    padding: 5%;
}
.create-form {
    display: flex;
}

.v-line {
    border-left : solid #111111;
    height : 50vw;
    margin: 10px;
}


</style>