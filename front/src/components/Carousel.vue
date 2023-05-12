<template>
  <Carousel :autoplay="2000" :wrap-around="true">
    <Slide v-for="slide in slides" :key="slide.id">
      <div class="carousel__item">
        <h3>{{ slide.title }}</h3>
        <p>{{ slide.content }}</p>
      </div>
    </Slide>

    <template #addons>
      <Navigation />
      <Pagination />
    </template>
  </Carousel>
</template>

<script setup>
import 'vue3-carousel/dist/carousel.css'
import { Carousel, Slide, Navigation } from 'vue3-carousel'

  const slides = [
    { id: '1', title: 'Vue 3 Introduction', content: 'VueJS is a library' },
    { id: '2', title: 'Vue 3 Components', content: 'Know the components' },
    { id: '3', title: 'Vue 3 Conditional', content: 'Rendering Conditionally' }
  ]

</script>