import {
  FormControl, InputLabel, NativeSelect
} from "@mui/material";
import AppBar from "@mui/material/AppBar";
import Box from "@mui/material/Box";
import Toolbar from "@mui/material/Toolbar";
import Typography from "@mui/material/Typography";
import { useNavigate } from 'react-router-dom';

type ProcessCardProps = {
  process: any;
}

export const SubHeader = (props: ProcessCardProps) => {
  
  let navigate = useNavigate();

  return (
    <>
    <Box sx={{ flexGrow: 1, width: "100%", position: "fixed", top: 80 }}>
      <AppBar
        position="static"
        style={{ background: "white", color: "black", padding: 0}}
      >
        <Toolbar>
          <FormControl sx={{ width: 1 / 8 }}>
            <InputLabel variant="standard" htmlFor="uncontrolled-native">
              Department
            </InputLabel>
            <NativeSelect
              defaultValue={10}
              inputProps={{
                name: "department",
                id: "uncontrolled-native",
              }}
            >
              <option value={10}>Management</option>
              <option value={10}>HR</option>
              <option value={10}>Production</option>
              <option value={10}>Sales Department</option>
            </NativeSelect>
          </FormControl>

          <Box width={30} />
          <Typography
            variant="h6"
            component="div"
            sx={{ flexGrow: 1, fontWeight: "bold" }}
          ></Typography>

          <Box
            sx={{
              width: 40,
              height: 40,
              borderRadius: 3,
              backgroundColor: "#33CC00", /*green*/
              "&:hover": {
                cursor: "pointer",
                boxShadow: 8,
                
              },
            }}
            onClick={() => {
              console.log("Green Box Click")
              navigate("/energy/:energySumYear" + props.process.energySumYear);
          }}
          ></Box>

          <h5 style={{ color: "black", padding: 20 }}>Niedrig </h5>

          <Box
            sx={{
              width: 40,
              height: 40,
              borderRadius: 3,
              backgroundColor: "#FFEF00", /*yellow*/
              "&:hover": {
                cursor: "pointer",
                boxShadow: 8,
                
              },
            }}
            onClick={() => {
              console.log("Yellow Box Click")
              navigate("/energy/:energySumYear" + props.process.energySumYear);
          }}
          ></Box>
          <h5 style={{ color: "black", padding: 20 }}>Mittel </h5>

          <Box
            sx={{
              width: 40,
              height: 40,
              borderRadius: 3,
              backgroundColor: "#FF3300",/*red*/ 
              "&:hover": {
                cursor: "pointer",
                boxShadow: 8,
                
              },
            }}
            onClick={() => {
              console.log("Red Box Click")
              navigate("/energy/:energySumYear" + props.process.energySumYear);
          }}
          ></Box>
          <h5 style={{ color: "black", padding: 20 }}>Hoch </h5>
        </Toolbar>
      </AppBar>
    </Box>
    <Box height={100}/>

    </>
  );
}
