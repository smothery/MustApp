<template>
  <div class="app-container home">
    <el-row :gutter="20">
      <el-col>
        <el-card class="box-card">
          <el-col :span="12">
            <svg-icon icon-class="user"></svg-icon>
          </el-col>
          <el-col :span="12">
            <div class="card-panel-text">在线用户数量</div>
            <div class="card-panel-num">{{ this.onlineNum }}</div>
          </el-col>
        </el-card>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="24">
        <div class="chart section" id="statisOnline" ref="statisOnlineChart">
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import {list, statis} from "@/api/monitor/online";
import * as echarts from 'echarts';

export default {
  name: "StatisIndex",
  data() {
    return {
      onlineNum: 10000,
      chartData: {}
    }
  },
  created() {
    this.loading = true;
    list().then(response => {
      this.onlineNum = response.total;
    });
    statis().then(response => {
      this.chartData = response.rows;
    });
    this.loading = false;
    setTimeout(() => {
      this.getChart();
    }, 1000);
  },
  methods: {
    getChart() {
      let myChart = echarts.init(this.$refs.statisOnlineChart);
      let option = {
        title: {
          text: '在线用户趋势'
        },
        tooltip: {
          trigger: 'axis'
        },
        grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true
        },
        xAxis: {
          type: 'category',
          data: []
        },
        yAxis: {
          type: 'value'
        },
        series: [
          {
            data: [],
            type: 'line'
          }
        ]
      };

      this.chartData.forEach((item,index) => {
        option.xAxis.data.push(item.createTime)
        option.series[0].data.push(item.onlineNum)
      });

      myChart.setOption(option);
    }
  }
};
</script>

<style scoped lang="scss">
.el-row {
  margin-bottom: 20px;

  &:last-child {
    margin-bottom: 0;
  }
}

.dashboard {

  &-container {
    padding: 15px;
    background-color: #f0f2f5;
  }

  &-text {
    font-size: 14px;
    line-height: 22px;
    padding-bottom: 15px;
  }
}

.echarts {
  width: 100%;
  height: 100%;
}

.box-card {
  height: 108px;
}

.chart {
  height: 350px;
}

.section {
  padding: 20px;
  background-color: white;
  border: 1px solid #ebeef5;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, .1);
}

.box-card > div > div > .svg-icon {
  width: 4em;
  height: 4em;
  color: #34bfa3;
}

.card-panel-text {
  padding-top: 1px;
  font-size: 16px;
  color: gray;
}

.card-panel-num {
  padding-top: 10px;
  font-size: 20px;
  font-weight: bold;
}
</style>

