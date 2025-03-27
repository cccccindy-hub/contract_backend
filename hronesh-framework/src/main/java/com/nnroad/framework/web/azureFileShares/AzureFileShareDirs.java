package com.nnroad.framework.web.azureFileShares;

import com.nnroad.common.utils.StringUtils;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.stream.Collectors;

@NoArgsConstructor
public class AzureFileShareDirs {
    private String directory;

    private AzureFileShareDirs(AzureFileShareDirsConstants azureFileShareDirsConstants, String subdirs) {
        directory = azureFileShareDirsConstants.getValue();
        if(StringUtils.isNotEmpty(subdirs)) {
            if(subdirs.endsWith("/")) subdirs = subdirs.substring(0, subdirs.length()-1);
            directory += (subdirs.startsWith("/") ? subdirs : "/"+subdirs);
        }
    }

    public static AzureFileShareDirs create(@NotNull AzureFileShareDirsConstants azureFileShareDirsConstants, Object... subdirs) {
        String subdirsStr = Arrays.stream(subdirs)
                .map(o-> o instanceof AzureFileShareDirsConstants ? ((AzureFileShareDirsConstants) o).getValue() : String.valueOf(o))
                .collect(Collectors.joining("/"));
        return new AzureFileShareDirs(azureFileShareDirsConstants, subdirsStr);
    }

    protected String getDirectory() {
        return directory;
    }

    private void setDirectory(String directory) {
        this.directory = directory;
    }
}
