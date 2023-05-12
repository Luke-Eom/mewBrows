<template>
   <footer class="text-muted py-5">
       <div class="container">
         <p class="float-end mb-1">
           <a href="#">Back to top</a>
         </p>
         <p class="mb-1"> Address :  </p>
         <p class="mb-0"> 인스타그램에서 더 많은 사진을 확인하세요 <a :href="instagramUrl">@{{instagramUser}}</a> or read our <a href="/docs/5.3/getting-started/introduction/">getting started guide</a>.</p>
       </div>
     </footer>
</template>

<script>
export default {
  name: 'Footer',
  data() {
    return {
        instagramUser: 'mew_brow',
        instagramUrl: 'https://www.instagram.com/mewbrow_official/?igshid=NzAzN2Q1NTE%3D'
    }
  }


}
</script>


<style scoped>

</style>
