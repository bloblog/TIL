<template>
    <div>
        <div class="container-xxl">
            <div class="container">
                <div class="text-center mx-auto mb-5 wow fadeInUp" data-wow-delay="0.1s" style="max-width: 600px;">
                    <h2 class="mb-3">가장 인기많은 회고 3개를 추려봤어요.</h2>
                </div>
                <div class="row g-4">
                    <div class="col-lg-4 col-sm-4 wow fadeInUp" data-wow-delay="0.1s">
                        <router-link to="/reviewDetail" @click="select" class="cat-item d-block bg-light text-center rounded p-3">
                            <div class="rounded">
                                
                                <div class="icon mb-3">
                                    <img class="img-fluid" src="@/assets/images/review-thumbnail.png" alt="Icon">
                                </div>
                                <div>
                                    <div>우리 스터디가 짱</div>
                                    <div>작성자 : soyi</div>
                                </div>
                            </div>
                        </router-link>
                    </div>
                    <div class="col-lg-4 col-sm-4 wow fadeInUp" data-wow-delay="0.3s">
                        <router-link to="/reviewDetail" @click="select" class="cat-item d-block bg-light text-center rounded p-3">
                        
                            <div class="rounded">
                                <div class="icon mb-3">
                                    <img class="img-fluid" src="@/assets/images/review-thumbnail.png" alt="Icon">
                                </div>
                                
                                <div>
                                    <div>즐거운 스터디~</div>
                                    <div>작성자 : hyeseung</div>
                                </div>
                            </div>
                        </router-link>
                    </div>
                    <div class="col-lg-4 col-sm-4 wow fadeInUp" data-wow-delay="0.5s"  >
                        <router-link to="/reviewDetail" @click="select" class="cat-item d-block bg-light text-center rounded p-3">
                            <div class="rounded">
                                <div class="icon mb-3">
                                    <img class="img-fluid" src="@/assets/images/review-thumbnail.png" alt="Icon">
                                </div>
                                <div>
                                    <div>다들 멋져</div>
                                    <div>작성자 : ssafy</div>
                                </div>
                            </div>
                        </router-link>
                    </div>
                    
                </div>
            </div>
        </div>
        
        <div class="more-button">
            <router-link to="/reviewDetailView" class="btn py-3 px-5 mt-3" style="background-color: #0DC5B8; border-radius: 30px; color: white;">더보기</router-link>
        </div>

    </div>
</template>

<script setup>
import {ref} from 'vue'

const reviewKey = ref(0);

</script>

<style scoped>
.cat-item {
  text-decoration: none;
  color: #7A6F56;
}

.more-button {
    text-align: end;
    margin-right: 5%;
}
</style>