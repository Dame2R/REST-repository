import { Stack, Grid } from "@mui/material";
import NavBar from "../components/Navbar";
import { ProcessCard } from "../components/ProcessCard";
import SubHeader from "../components/Subheader";
import mock_data from "../mock/mock_data.json"

const processes = mock_data.map((process) => (
    <Grid item xs={12} sm={12} md={6} lg={4} xl={3}>
    <ProcessCard process={process}/>
    </Grid>
));

export const Home = () =>  {
  return (
    <div>
    <Stack>
      <NavBar />
      <SubHeader />
    </Stack>
    <Grid container spacing={3} paddingTop={10}>
      {processes}
    </Grid>
    </div>
  );
};
