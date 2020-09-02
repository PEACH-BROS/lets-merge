<template>
  <div class="mission-container">
    <span class="mission-item">
      {{ mission.name }}
      {{ mission.startDateTime }}
      {{ mission.dueDateTime }}
    </span>

    <v-btn
      v-if="mission.status === 'ASSIGNED'"
      depressed
      color="#82b3c9"
      @click="cancel"
    >
      신청취소
    </v-btn>
    <v-btn
      v-else-if="mission.status === 'ASSIGNABLE'"
      depressed
      color="#b3e5fc"
      @click="assign"
    >
      신청하기
    </v-btn>
    <v-btn v-else-if="mission.status === 'NO_VACANCY'" depressed disabled>
      정원초과
    </v-btn>
    <v-btn v-else-if="mission.status === 'CLOSED'" depressed disabled>
      모집마감
    </v-btn>
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
      alert("ASSIGN");
    },
    cancel() {
      alert("CANCEL");
    },
  },
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
