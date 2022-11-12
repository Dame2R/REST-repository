import { Stack, Box } from "@mui/material";
import ProcessTypeRow from "./ProcessTypeRow";
import SubHeader from "./Subheader";

type ProcessLandscapeProps = {
    parentProcess?: any;
  coreProcesses: any;
  managementProcesses: any;
  supportProcesses: any;
};

export default function ProcessLandscape(props: ProcessLandscapeProps) {
  return (
    <>
      <SubHeader />
      <Box padding={5}>
        <Stack spacing={5}>
          <ProcessTypeRow
            title="Management"
            processes={props.managementProcesses}
          />
          <ProcessTypeRow title="Core" processes={props.coreProcesses} />
          <ProcessTypeRow title="Support" processes={props.supportProcesses} />
        </Stack>
      </Box>
    </>
  );
}
