import Avatar from "@mui/material/Avatar";

const ChartBoy = () => {
  return (
    <div>
      <h2>남자아이돌 차트</h2>
      <div id="chart-container">
        <div id="rank-1-idol">
          <Avatar
            src="../assets/NCT_제노.PNG"
            sx={{ width: "4rem", height: "4rem" }}
          />
        </div>
        <div id="rank-23-idol">
          <div>
            <Avatar src="../assets/NCT_제노.PNG" />
          </div>
          <div>
            <Avatar src="../assets/NCT_제노.PNG" />
          </div>
        </div>
      </div>
    </div>
  );
};

export default ChartBoy;
