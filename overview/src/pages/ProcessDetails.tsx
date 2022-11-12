import { Stack } from "@mui/material";
import NavBar from "../components/Navbar";
import { useProcessStore } from "../stores/process_store";
import ProcessLandscape from "../components/ProcessLandscape";
import { useParams } from "react-router-dom";

export default function ProcessDetails() {
  const { id } = useParams();

  const allProcesses: any = useProcessStore((state) => state.allProcesses);

  const parentProcessIndex = allProcesses.findIndex(
    (p: any) => p.id == id
  );

  return (
    <div>
      <Stack>
        <NavBar
          title={"Übersicht: " + allProcesses[parentProcessIndex].name}
        />
        <ProcessLandscape
          parentProcess={allProcesses[parentProcessIndex]}
          coreProcesses={allProcesses.filter(
            (p: any) => p.parentProcess === id && p.processType === "core"
          )}
          managementProcesses={allProcesses.filter(
            (p: any) => p.parentProcess === id && p.processType === "management"
          )}
          supportProcesses={allProcesses.filter((p: any) => p.parentProcess === id && p.processType === "support")}
        />
      </Stack>
    </div>
  );
}
