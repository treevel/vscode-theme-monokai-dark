const fs = require('fs');
const path = require('path');

class FileManager {
  constructor(currentDirectory) {
    this.currentDirectory = currentDirectory || '.';
  }

  list() {
    const files = fs.readdirSync(this.currentDirectory);
    console.log('Files in current directory:');
    files.forEach(file => {
      console.log(file);
    });
  }

  createDirectory(directoryName) {
    fs.mkdirSync(path.join(this.currentDirectory, directoryName));
    console.log(`${directoryName} created successfully!`);
  }

  deleteDirectory(directoryName) {
    fs.rmdirSync(path.join(this.currentDirectory, directoryName), { recursive: true });
    console.log(`${directoryName} deleted successfully!`);
  }

  createFile(fileName, content) {
    fs.writeFileSync(path.join(this.currentDirectory, fileName), content);
    console.log(`${fileName} created successfully!`);
  }

  deleteFile(fileName) {
    fs.unlinkSync(path.join(this.currentDirectory, fileName));
    console.log(`${fileName} deleted successfully!`);
  }

  copyFile(sourceFileName, destinationFileName) {
    fs.copyFileSync(path.join(this.currentDirectory, sourceFileName), path.join(this.currentDirectory, destinationFileName));
    console.log(`${sourceFileName} copied to ${destinationFileName} successfully!`);
  }

  moveFile(sourceFileName, destinationFileName) {
    fs.renameSync(path.join(this.currentDirectory, sourceFileName), path.join(this.currentDirectory, destinationFileName));
    console.log(`${sourceFileName} moved to ${destinationFileName} successfully!`);
  }
}

// Example Usage
const fileManager = new FileManager();
fileManager.list();
fileManager.createDirectory('newDirectory');
fileManager.createFile('newFile.txt', 'This is a new file!');
fileManager.copyFile('newFile.txt', 'newFileCopy.txt');
fileManager.moveFile('newFile.txt', 'newDirectory/newFile.txt');
fileManager.deleteFile('newFile.txt');
fileManager.deleteDirectory('newDirectory');
