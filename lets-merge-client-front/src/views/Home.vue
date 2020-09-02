<template>
  <div class="mission-container">
    <div class="font-weight-bold text-h5 align-self-start">나의 미션</div>
    <MissionItem
      v-for="mission in missions"
      :key="mission.id"
      :mission="mission"
    />

    <div class="font-weight-bold text-h5 align-self-start mt-16">모집 중인 미션</div>
    <MissionItem
      v-for="mission in missions"
      :key="mission.id"
      :mission="mission"
    />

    <div class="font-weight-bold text-h5 align-self-start mt-16">모집이 끝난 미션</div>
    <MissionItem
      v-for="mission in missions"
      :key="mission.id"
      :mission="mission"
    />
  </div>
</template>

<script>
import mockMissions from "@/utils/mockMissions";
import MissionItem from "@/views/MissionItem";

export default {
  name: "Home",
  components: {
    MissionItem,
  },
  data() {
    return {
      missions: mockMissions.MISSIONS,
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
  margin: 0;
}
</style>
