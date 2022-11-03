import LogoutIcon from "@mui/icons-material/Logout";
import { IconButton, Tooltip } from "@mui/material";
import AppBar from "@mui/material/AppBar";
import Box from "@mui/material/Box";
import Toolbar from "@mui/material/Toolbar";
import Typography from "@mui/material/Typography";
import { useNavigate } from "react-router-dom";
import logo from "../images/logo.svg";

export default function NavBar() {
  const navigate = useNavigate();

  return (

    <>
    <Box sx={{ flexGrow: 1, top: 0, position: "fixed", width: "100%", zIndex: 500 }}>
      <AppBar
        position="static"
        style={{ background: "white", color: "black", padding: 0 }}
      >
        <Toolbar>
          <Box
            component="img"
            sx={{
              height: 80,
              cursor: "pointer",
            }}
            alt="Logo"
            src={logo}
            onClick={() => {
              navigate("/");
            }}
          />
          <Box width={20} />

          <Typography
            variant="h6"
            component="div"
            sx={{ flexGrow: 1, fontWeight: "bold" }}
          >
            Übersicht
          </Typography>
          <Tooltip title="Logout">
            <IconButton
              size="large"
              edge="start"
              color="inherit"
              aria-label="menu"
              sx={{ mr: 2 }}
            >
              <LogoutIcon />
            </IconButton>
          </Tooltip>
        </Toolbar>
      </AppBar>
    </Box>
    <Box height={80}/>
    </>
  );
}
