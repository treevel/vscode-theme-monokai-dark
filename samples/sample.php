<?php

class FileManager
{
    private $rootDirectory;

    public function __construct($rootDirectory)
    {
        $this->rootDirectory = $rootDirectory;
    }

    public function getFilesInDirectory($directory)
    {
        $path = $this->rootDirectory . $directory;
        $files = scandir($path);
        $files = array_diff($files, array('.', '..'));

        return $files;
    }

    public function copyFile($source, $destination)
    {
        $sourcePath = $this->rootDirectory . $source;
        $destinationPath = $this->rootDirectory . $destination;

        if (!file_exists($sourcePath)) {
            throw new Exception('Source file does not exist');
        }

        if (file_exists($destinationPath)) {
            throw new Exception('Destination file already exists');
        }

        if (!copy($sourcePath, $destinationPath)) {
            throw new Exception('File copy failed');
        }
    }

    public function moveFile($source, $destination)
    {
        $sourcePath = $this->rootDirectory . $source;
        $destinationPath = $this->rootDirectory . $destination;

        if (!file_exists($sourcePath)) {
            throw new Exception('Source file does not exist');
        }

        if (file_exists($destinationPath)) {
            throw new Exception('Destination file already exists');
        }

        if (!rename($sourcePath, $destinationPath)) {
            throw new Exception('File move failed');
        }
    }

    public function deleteFile($path)
    {
        $fullPath = $this->rootDirectory . $path;

        if (!file_exists($fullPath)) {
            throw new Exception('File does not exist');
        }

        if (!unlink($fullPath)) {
            throw new Exception('File delete failed');
        }
    }
}

// Example Usage
$fileManager = new FileManager('/path/to/root/directory');

$files = $fileManager->getFilesInDirectory('/some/directory');
print_r($files);

$fileManager->copyFile('/file/to/copy', '/new/file/location');
$fileManager->moveFile('/file/to/move', '/new/file/location');
$fileManager->deleteFile('/file/to/delete');
