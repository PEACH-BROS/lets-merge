<template>
  <div class="mission-container">
    <span class="mission-item">
      <div class="text-h6">{{ mission.name }}</div>
      <div class="subtitle-2 font-weight-light">
        {{ mission.startDateTime }} ~ {{ mission.dueDateTime }}
      </div>
    </span>
    <div class="mission-item">
      <v-btn
        v-if="mission.status === '신청완료'"
        depressed
        color="#82b3c9"
        @click="cancel"
      >
        신청취소
      </v-btn>
      <v-btn
        v-else-if="mission.status === '신청가능'"
        depressed
        color="#b3e5fc"
        @click="assign"
      >
        신청하기
      </v-btn>
      <v-btn v-else-if="mission.status === '정원초과'" depressed disabled>
        정원초과
      </v-btn>
      <v-btn v-else-if="mission.status === '모집마감'" depressed disabled>
        모집마감
      </v-btn>
    </div>
  </div>
</template>

<script>
import api from "@/api/mission";

export default {
  name: "MissionItem",
  props: {
    mission: {
      type: Object,
      required: true,
    },
  },
  methods: {
    async assign() {
      await api.assignMission(this.mission.id);
      this.$store.commit("missions/ASSIGN_MISSION", this.mission.id);
    },
    async cancel() {
      await api.cancelMission(this.mission.id);
      this.$store.commit("missions/CANCEL_MISSION", this.mission.id);
    },
  },
};
</script>

<style scoped>
.mission-container {
  display: flex;
  flex-direction: row;
  flex-wrap: nowrap;
  align-items: baseline;
  justify-content: space-between;
  width: 100%;
  height: 10%;
}

.mission-item {
  padding: 10px;
  background-color: white;
  border-radius: 10px;
}
</style>
