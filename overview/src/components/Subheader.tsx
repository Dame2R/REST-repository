import {
  FormControl, InputLabel, NativeSelect
} from "@mui/material";
import AppBar from "@mui/material/AppBar";
import Box from "@mui/material/Box";
import Toolbar from "@mui/material/Toolbar";
import Typography from "@mui/material/Typography";
import { Stack } from "@mui/system";
import { useNavigate } from 'react-router-dom';



export default function SubHeader() {
  
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
               
              "&:hover": {
                cursor: "pointer",
                                
              },
        }}
        onClick={() => {
          console.log("Green Box Click")
          navigate("/energy/low");
        }}
        >
          <Stack direction={"row"}
          justifyContent="center"
          alignItems="center"
          >

          
            
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
            
          ></Box>
        <h5 style={{ color: "black", padding: 20 }}>Niedrig </h5>
          
        </Stack>
        </Box>



        <Box
     sx={{
               
              "&:hover": {
                cursor: "pointer",
                                
              },
            }}
            onClick={() => {
              console.log("Yellow Box Click")
              navigate("/energy/medium");
          }}
        >
          <Stack direction={"row"}
          justifyContent="center"
          alignItems="center"
          >

          
            

          
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
          ></Box>
          <h5 style={{ color: "black", padding: 20 }}>Mittel </h5>
          </Stack>
        </Box>

        <Box
     sx={{
               
              "&:hover": {
                cursor: "pointer",
                                
              },
            }}
            onClick={() => {
              console.log("Red Box Click")
              navigate("/energy/high");
          }}
        >
          <Stack direction={"row"}
          justifyContent="center"
          alignItems="center"
          >

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
          ></Box>
          <h5 style={{ color: "black", padding: 20 }}>Hoch </h5>
          </Stack>
        </Box>
        </Toolbar>
      </AppBar>
    </Box>
    <Box height={100}/>

    </>
  );
}
