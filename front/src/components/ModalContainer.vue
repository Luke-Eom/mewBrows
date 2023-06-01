<template>
  <div>
    <component
        v-for="modal in modals"
        :key="modal.key"
        :is="modal.component"
        v-bind="modal.props"
        @resolve="(value) => onResolve(value, modal.key, modal.resolve)"
        @reject="(value) => onReject(value, modal.key, modal.reject)"
        @close="() => closeModal(modal.key)"
    />
  </div>
</template>

<script>
import { markRaw } from 'vue';

export default {
  name: 'ModalContainer',
  data: () => ({
    modals: []
  }),
  methods: {
    addModal(key, component, configs) {
      const props = configs?.props ?? {};
      this.closeModal(key);
      return new Promise((resolve, reject) => {
        this.modals.push({
          key,
          component: markRaw(component),
          props,
          resolve,
          reject
        });
      });
    },
    closeModal(key) {
      this.modals = this.modals.filter(modalInfo => {
        if (modalInfo.key === key) {
          modalInfo.reject();
          return false;
        } else {
          return true;
        }
      });
    },
    onResolve(value, key, resolve) {
      resolve(value);
      this.closeModal(key);
    },
    onReject(value, key, reject) {
      reject(value);
      this.closeModal(key);
    }
  }
};
</script>


<style scoped>

</style>