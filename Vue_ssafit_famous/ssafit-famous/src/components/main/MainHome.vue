<template>
    <div>
        <div class="container-global" data-wow-delay="0.1s" style="margin-right: 10px; margin-left: 10px;">
            
            <!-- Spinner Start -->
            <!-- <div id="spinner" class="show bg-white position-fixed translate-middle w-100 vh-100 top-50 start-50 d-flex align-items-center justify-content-center">
                <div class="spinner-border text-primary" style="width: 3rem; height: 3rem;" role="status">
                    <span class="sr-only"></span>
                </div>
            </div> -->
            <!-- Spinner End -->

            <CarouselItem/>

            <MainReview/>

            <FooterBar/>
        
        </div>
    </div>
    
</template>

<script setup>
import CarouselItem from '@/components/main/CarouselItem.vue';
import MainReview from '@/components/review/MainReview.vue';
import FooterBar from '../common/FooterBar.vue';
</script>

<style scoped>
.container-global {
    background-color: #fffae49f;
}
</style>