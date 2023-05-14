import os
import shutil

class FileExplorer:
    def __init__(self, path="."):
        self.path = path

    def list_files(self):
        print("Files in directory:", self.path)
        for filename in os.listdir(self.path):
            print(filename)

    def list_directories(self):
        print("Directories in directory:", self.path)
        for dirname in os.listdir(self.path):
            if os.path.isdir(dirname):
                print(dirname)

    def create_directory(self, dirname):
        try:
            os.mkdir(os.path.join(self.path, dirname))
            print("Directory created successfully.")
        except OSError as e:
            print("Directory creation failed:", e)

    def delete_directory(self, dirname):
        try:
            shutil.rmtree(os.path.join(self.path, dirname))
            print("Directory deleted successfully.")
        except OSError as e:
            print("Directory deletion failed:", e)

    def copy_file(self, src_file, dest_file):
        try:
            shutil.copy(src_file, dest_file)
            print("File copied successfully.")
        except OSError as e:
            print("File copy failed:", e)

    def move_file(self, src_file, dest_file):
        try:
            shutil.move(src_file, dest_file)
            print("File moved successfully.")
        except OSError as e:
            print("File move failed:", e)

if __name__ == '__main__':
    explorer = FileExplorer()
    explorer.list_files()
    explorer.list_directories()
    explorer.create_directory("new_directory")
    explorer.delete_directory("new_directory")
    explorer.copy_file("test.txt", "test_copy.txt")
    explorer.move_file("test_copy.txt", "new_directory/test_copy.txt")
