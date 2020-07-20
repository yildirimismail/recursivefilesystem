import com.fsystem.domain.entity.File;
import com.fsystem.domain.entity.Folder;
import com.fsystem.repository.FileRepository;
import com.fsystem.repository.FolderRepository;
import com.fsystem.service.impl.FileSystemServiceImpl;
import helper.ServiceTestBase;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

public class FileSystemService_InitializeTree extends ServiceTestBase {
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
    public void noNeighborTree(){
        Folder rootFolder = new Folder();
        rootFolder.setId(1);
        when(folderRepository.findRootFolder()).thenReturn(rootFolder);
        when(folderRepository.findFoldersByFolderId(rootFolder.getId())).thenReturn(new ArrayList<>());
        assertEquals(0,fileSystemService.initiliazeTree().getNeighbors().size());
    }

    @Test
    public void oneSubFolderTree(){
        Folder rootFolder = new Folder();
        rootFolder.setId(1);
        when(folderRepository.findRootFolder()).thenReturn(rootFolder);
        List<Folder> subFolderList= new ArrayList<>();
        subFolderList.add(populateFolder(2,"Files",rootFolder));

        when(folderRepository.findFoldersByFolderId(rootFolder.getId())).thenReturn(subFolderList);
        assertEquals(1,fileSystemService.initiliazeTree().getNeighbors().size());
    }

    @Test
    public void oneSubFolderAndOneFileTree(){
        Folder rootFolder = new Folder();
        rootFolder.setId(1);
        when(folderRepository.findRootFolder()).thenReturn(rootFolder);
        List<Folder> subFolderList= new ArrayList<>();

        subFolderList.add(populateFolder(2,"Files",rootFolder));

        List<File> fileList = new ArrayList<>();
        fileList.add(populateFile(1,"mysql.exe",rootFolder));
        when(folderRepository.findFoldersByFolderId(rootFolder.getId())).thenReturn(subFolderList);
        when(fileRepository.findFilesByFolderId(rootFolder.getId())).thenReturn(fileList);
        assertEquals(2,fileSystemService.initiliazeTree().getNeighbors().size());
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
