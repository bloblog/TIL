import { createRouter, createWebHistory } from 'vue-router'

import MainView from '@/views/MainView.vue'
import MainHome from '@/components/main/MainHome.vue'
import ReviewBoard from '@/views/reviewDetailView.vue'
import MyInfo from '@/components/user/MyInfo.vue'
import MyInfoView from '@/views/MyInfoView.vue'

import DashBoard from '../views/DashBoardView.vue'
import CreateStudy from '../views/CreateStudyView.vue'

import OngoingList from '../components/study/OngoingList.vue'
import CompleteList from '../components/study/CompleteList.vue'
import MyReview from '../components/review/MyReview.vue'

import studyDetail from '../views/StudyInfoView.vue'
import reviewDetail from '../components/review/ReviewDetail.vue'
import createReview from '../components/review/createReview.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/nested/index.html',
      name: 'main',
      component: MainView,
      redirect: {name: 'mainHome'},
      children: [
        { path : '/mainHome',
          name : 'mainHome',
          component: MainHome
        }, // 맨 처음 뜨는 홈 화면
        { path : '/reviewDetailView',
          name : 'reviewBoard',
          component: ReviewBoard
        }, // 회고 게시판으로 넘어가
        
      ]
    },
    { path: '/infoView',
      name: 'infoView',
      component: MyInfoView,
      redirect: {name: 'myinfo'},
      children:[
        { path: '/myinfo',
          name: 'myinfo',
          component: MyInfo
        }, // 마이페이지 내부 회원정보 뷰
        {
          path: '/ongoing',
          name: 'ongoing',
          component: OngoingList
        }, // 마이페이지 내부 진행중인 스터디 뷰
        {
          path: '/done',
          name: 'done',
          component: CompleteList
        }, // 마이페이지 내부 지난 스터디 뷰
        {
          path: '/myreview',
          name: 'myreview',
          component: MyReview
        }, // 마이페이지 내부 작성한 회고 뷰
      ]
    }, // 마이페이지
    { path: '/dashboard',
      name: 'dashboard',
      component: DashBoard
    },
    { path: '/createStudy',
      name: 'createStudy',
      component: CreateStudy
    },
    {
      path: '/studyDetail',
      name: 'studyDetail',
      component: studyDetail
    },
    {
      path: '/reviewDetail',
      name: 'reviewDetail',
      component: reviewDetail
    },
    {
      path: '/createReview',
      name: 'createReview',
      component: createReview
    }
  ]
})

export default router
