import com.fsystem.domain.entity.File;
import com.fsystem.domain.entity.Folder;
import com.fsystem.repository.FileRepository;
import com.fsystem.repository.FolderRepository;
import com.fsystem.service.impl.FileSystemServiceImpl;
import helper.ServiceTestBase;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.primefaces.model.DefaultTreeNode;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

public class FileSystemService_FillTreeComponent extends ServiceTestBase {
    @InjectMocks
    private FileSystemServiceImpl fileSystemService;
    @Mock
    private FileRepository fileRepository;
    @Mock
    private FolderRepository folderRepository;

    @Override
    public void setup() {
        super.setup();
    }

    @Test
    public void searchAbsoluteFindOnTree(){
        Folder rootFolder = new Folder();
        rootFolder.setId(1);
        rootFolder.setName("C://");
        when(folderRepository.findRootFolder()).thenReturn(rootFolder);
        List<Folder> subFolderList= new ArrayList<>();

        subFolderList.add(populateFolder(2,"myFiles",rootFolder));
        Folder subRootFolder = populateFolder(3,"Program",rootFolder);
        subFolderList.add(subRootFolder);

        List<Folder> subRootFolderList = new ArrayList<>();
        subRootFolderList.add( populateFolder(4,"Java",subRootFolder));
        subRootFolderList.add(populateFolder(5,"mysql",subRootFolder));

        when(folderRepository.findFoldersByFolderId(subRootFolder.getId())).thenReturn(subRootFolderList);

        List<File> fileList = new ArrayList<>();
        fileList.add(populateFile(1,"mysql.exe",subRootFolder));
        when(folderRepository.findFoldersByFolderId(rootFolder.getId())).thenReturn(subFolderList);
        when(fileRepository.findFilesByFolderId(rootFolder.getId())).thenReturn(fileList);

        assertEquals(3,fileSystemService.fillTreeComponent(fileSystemService.initiliazeTree(), new DefaultTreeNode("root", null)).getChildCount());
    }

    public File populateFile(int id, String value, Folder parentFolder){
        File file = new File();
        file.setId(id);
        file.setName(value);
        file.setParentFolder(parentFolder);
        return file;
    }
    public Folder populateFolder(int id, String value, Folder parentFolder){
        Folder folder = new Folder();
        folder.setId(id);
        folder.setName(value);
        folder.setParentFolder(parentFolder);
        return folder;
    }
}
