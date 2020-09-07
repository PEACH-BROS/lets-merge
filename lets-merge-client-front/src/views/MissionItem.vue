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
        v-show="mission.status === '신청완료'"
        depressed
        color="#ef9a9a"
        @click="cancel"
      >
        신청취소
      </v-btn>
      <v-btn
        v-show="mission.status === '신청가능'"
        depressed
        color="#90caf9"
        @click="assign"
      >
        신청하기
      </v-btn>
      <v-btn v-show="mission.status === '정원초과'" depressed disabled>
        정원초과
      </v-btn>
      <v-btn v-show="mission.status === '모집마감'" depressed disabled>
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
      if (this.mission.status !== '신청가능') {
        this.$store.commit("snackbar/SHOW", "이미 신청한 미션입니다.");
        return;
      }
      await api.assignMission(this.mission.id);
      this.$store.commit("missions/ASSIGN_MISSION", this.mission.id);
      this.$store.commit("snackbar/SHOW", this.mission.name + "을 신청했습니다.");
    },
    async cancel() {
      if (this.mission.status !== '신청완료') {
        this.$store.commit("snackbar/SHOW", "해당 미션의 신청 내역이 없습니다.");
        return;
      }
      await api.cancelMission(this.mission.id);
      this.$store.commit("missions/CANCEL_MISSION", this.mission.id);
      this.$store.commit("snackbar/SHOW", this.mission.name + " 신청을 취소했습니다.");

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
