<template>
    <div>
        <div>
            <div>
                <input v-model="searchId" class="form-control rounded" placeholder="아이디로 검색하세요"/>
            </div>
            <div>
                <p>검색결과</p>
                <table class="table table-bordered table-hover table-secondary">
                    <thead>
                        <tr>
                        <th scope="col">idx</th>
                        <th scope="col">ID</th>
                        <th scope="col">Exp</th>
                        <th scope="col"></th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="(id, index) in searchResult">
                            <th scope="row">{{ index+1 }}</th>
                            <td>{{ id }}</td>
                            <td>100</td>
                            <td>
                                <svg @click="addUser(id)" xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-plus-lg" viewBox="0 0 16 16">
                                    <path fill-rule="evenodd" d="M8 2a.5.5 0 0 1 .5.5v5h5a.5.5 0 0 1 0 1h-5v5a.5.5 0 0 1-1 0v-5h-5a.5.5 0 0 1 0-1h5v-5A.5.5 0 0 1 8 2Z"/>
                                </svg>
                            </td>
                        
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <div>
            <p>결과 리스트</p>
            <div>
                <table class="table table-bordered table-hover table-secondary">
                    <thead>
                        <tr>
                        <th scope="col">idx</th>
                        <th scope="col">ID</th>
                        <th scope="col">Exp</th>
                        <th scope="col"></th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="(id, index) in memberList">
                            <th scope="row">{{ index+1 }}</th>
                            <td>{{ id }}</td>
                            <td>100</td>
                            <td>
                                <svg @click="deleteUser(id)" xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-x-lg" viewBox="0 0 16 16">
                                    <path d="M2.146 2.854a.5.5 0 1 1 .708-.708L8 7.293l5.146-5.147a.5.5 0 0 1 .708.708L8.707 8l5.147 5.146a.5.5 0 0 1-.708.708L8 8.707l-5.146 5.147a.5.5 0 0 1-.708-.708L7.293 8 2.146 2.854Z"/>
                                </svg>
                            </td>
                        
                        </tr>
                    </tbody>
                </table>

            </div>
        </div>
    </div>
</template>

<script setup>
import {ref} from 'vue';

const searchId = ref(null);

const memberList = ref(['soyi', 'hyeseung']);
const searchResult = ref(['soyi1', 'soyi2']);

const outUser = ref([]);
const inUser = ref([]);

// 뭔가 로직이 이상한데... 일단 킵
const deleteUser = function(id) {
    if (!outUser.value.includes(id)) {
        outUser.value.push(id);
    }
    memberList.value.splice(id,1);
    if (!searchResult.value.includes(id)) {
        searchResult.value.push(id);
    }
}

const addUser = function(id) {
    if (!inUser.value.includes(id)) {
        inUser.value.push(id);
    }
    if (!memberList.value.includes(id)) {
        memberList.value.push(id);
    }
    searchResult.value.splice(id, 1);
    
}

</script>

<style scoped>
</style>