import { Stack } from "@mui/material";
import NavBar from "../components/Navbar";
import { useProcessStore } from "../stores/process_store";
import ProcessLandscape from "../components/ProcessLandscape";
import { useParams } from "react-router-dom";

export default function ProcessDetails() {
  const { id } = useParams();

  const allProcesses = useProcessStore((state) => state.allProcesses);

  const parentProcessIndex = allProcesses.findIndex(
    (p: any) => p.id === id
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
            p => p.parent === id && p.type === "core"
          )}
          managementProcesses={allProcesses.filter(
            p => p.parent === id && p.type === "management"
          )}
          supportProcesses={allProcesses.filter(p => p.parent === id && p.type === "support")}
        />
      </Stack>
    </div>
  );
}
