<template>
    <div class="col-6">
        <form>
            <div>
                <div class="col-10">
                    <label>스터디명</label>
                    <input v-model="studyName" class="form-control" placeholder="스터디 이름을 입력하세요">
                </div>
            </div>
            <div>
                <div class="col-10">
                    <label>카테고리</label>
                    <select id="inputState" class="form-select" v-model="category">
                        <option disabled>선택</option>
                        <option type="radio" id="coding" name="category" value="coding">코딩</option>
                        <option type="radio" id="fitness" name="category" value="fitness">운동</option>
                        <option type="radio" id="hobby" name="category" value="hobby">취미</option>
                        <option type="radio" id="reading" name="category" value="reading">독서</option>
                    </select>
                </div>
            </div>
            <div class="col-10" style="display: flex; justify-content: space-evenly;">
                <div class="col-6 p-2" >
                    <label>시작일</label>
                    <VueDatePicker v-model="startDate"></VueDatePicker>
                </div>
                <div class="col-6 p-2">
                    <label>종료일</label>
                    <VueDatePicker v-model="endDate"></VueDatePicker>
                </div>
            </div>
                
            <div>
                <div class="col-10">
                    <label>알림주기 </label>
                    <input v-model="alarm" type="number" class="form-control" placeholder="몇일 전에 알림을 드릴까요?">
                </div>
            </div>
            
            
        </form>
    </div>
</template>

<script setup>
import VueDatePicker from '@vuepic/vue-datepicker';
import '@vuepic/vue-datepicker/dist/main.css'

import {ref} from 'vue'

const studyName = ref(null);
const category = ref("선택");
const startDate = ref(new Date());
const endDate = ref(new Date());
const alarm = ref();



</script>

<style scoped>

</style>