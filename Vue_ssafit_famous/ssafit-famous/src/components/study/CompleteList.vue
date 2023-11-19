<!-- 완료된 스터디 리스트 -->
<template>
    <div>
        <h3>지난 스터디</h3>
        <table>
            <thead>
                <tr>
                    <th>No</th>
                    <th>스터디명</th>
                    <th>카테고리</th>
                    <th>시작일</th>
                    <th>종료일</th>
                    <th>회고작성</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>1</td>
                    <td>빛나는 코드 마법사들</td>
                    <td>코딩</td>
                    <td>2023.09.30</td>
                    <td>2023.10.31</td>
                    <RouterLink to="createReview">회고 작성하러 가기</RouterLink>
                </tr>
                <tr>
                    <td>2</td>
                    <td>최승준과 아이들</td>
                    <td>운동</td>
                    <td>2023.09.01</td>
                    <td>2023.09.30</td>
                    <RouterLink to="reviewDetail">내가 작성한 회고 보기</RouterLink>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script setup>

</script>

<style scoped>

</style>