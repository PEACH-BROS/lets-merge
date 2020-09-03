<template>
  <div class="container">
    <div class="font-weight-bold text-h5 align-self-start">나의 미션</div>
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
    this.$store.commit("missions/SET_MISSIONS");
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
.container {
  display: flex;
  flex-direction: column;
  justify-content: center;
  flex-wrap: nowrap;
  align-items: center;
  align-self: center;
  width: 40%;
}

.container * {
  margin: 0;
}
</style>
