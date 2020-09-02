<template>
  <div class="mission-container">
    <span class="mission-item">
      {{ mission.name }}
      {{ mission.startDateTime }}
      {{ mission.dueDateTime }}
    </span>
    <div v-if="assignable">
    <v-btn v-if="isAssigned()" depressed @click="assign">신청하기</v-btn>
    <v-btn v-else depressed disabled>신청불가</v-btn>
    </div>
    <div v-if="!assignable">
      <v-btn depressed disabled>모집마감</v-btn>
    </div>
  </div>
</template>

<script>
export default {
  name: "MissionItem",
  props: {
    mission: {
      type: Object,
      required: true,
    },
    assignable: {
      type: Boolean,
      required: true,
    },
  },
  methods: {
    isAssigned() {
      return this.mission.active === "Y";
    },
    assign() {
      alert("ASSIGNED");
    }
  }
};
</script>

<style scoped>
.mission-container {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  width: 100%;
  height: 10%;
}
.mission-item {
  width: 70%;
  padding: 10px;
  background-color: white;
  border-radius: 10px;
}
</style>
