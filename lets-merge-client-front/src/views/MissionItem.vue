<template>
  <div class="mission-container">
    <span class="mission-item">
      {{ mission.name }}
      {{ mission.startDateTime }}
      {{ mission.dueDateTime }}
    </span>
    <v-btn depressed @click="assign">신청하기</v-btn>
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
  },
  methods: {
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
