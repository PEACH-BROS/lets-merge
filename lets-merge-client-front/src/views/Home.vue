<template>
  <div class="mission-container">
    <div class="font-weight-bold text-h5 align-self-start mt-16">나의 미션</div>
    <MissionItem
      v-for="mission in assignedMissions"
      :key="mission.id"
      :mission="mission"
    />

    <div class="font-weight-bold text-h5 align-self-start mt-16">
      모집 중인 미션
    </div>
    <MissionItem
      v-for="mission in openedMissions"
      :key="mission.id"
      :mission="mission"
    />

    <div class="font-weight-bold text-h5 align-self-start mt-16">
      종료된 미션
    </div>
    <MissionItem
      v-for="mission in closedMissions"
      :key="mission.id"
      :mission="mission"
    />
  </div>
</template>

<script>
import MissionItem from "@/views/MissionItem";

export default {
  name: "Home",
  components: {
    MissionItem,
  },
  created() {
    console.log("H3E")
    //TODO: API 호출 직후에 하기
    this.$store.commit("missions/SET_ASSIGNED_MISSIONS");
    this.$store.commit("missions/SET_OPENED_MISSIONS");
    this.$store.commit("missions/SET_CLOSED_MISSIONS");
  },
  data() {
    return {
      assignedMissions: this.$store.getters["missions/getAssignedMissions"],
      openedMissions: this.$store.getters["missions/getOpenedMissions"],
      closedMissions: this.$store.getters["missions/getClosedMissions"],
    };
  },
};
</script>

<style>
.mission-container {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.mission-container * {
  margin: 0px;
}
</style>
