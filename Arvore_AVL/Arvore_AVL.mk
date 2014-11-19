##
## Auto Generated makefile by CodeLite IDE
## any manual changes will be erased      
##
## Debug
ProjectName            :=Arvore_AVL
ConfigurationName      :=Debug
WorkspacePath          := "C:\Users\LAB4-PROFESSOR\Documents\GitHub\Netflix\Programa\Netflix\Trabalho"
ProjectPath            := "C:\Users\LAB4-PROFESSOR\Documents\GitHub\Trabalho-2-ProjAlgoritmosII\Arvore_AVL"
IntermediateDirectory  :=./Debug
OutDir                 := $(IntermediateDirectory)
CurrentFileName        :=
CurrentFilePath        :=
CurrentFileFullPath    :=
User                   :=LAB4-PROFESSOR
Date                   :=11/19/14
CodeLitePath           :="C:\Program Files\CodeLite"
LinkerName             :=C:/MinGW-4.8.1/bin/g++.exe 
SharedObjectLinkerName :=C:/MinGW-4.8.1/bin/g++.exe -shared -fPIC
ObjectSuffix           :=.o
DependSuffix           :=.o.d
PreprocessSuffix       :=.i
DebugSwitch            :=-g 
IncludeSwitch          :=-I
LibrarySwitch          :=-l
OutputSwitch           :=-o 
LibraryPathSwitch      :=-L
PreprocessorSwitch     :=-D
SourceSwitch           :=-c 
OutputFile             :=$(IntermediateDirectory)/$(ProjectName)
Preprocessors          :=
ObjectSwitch           :=-o 
ArchiveOutputSwitch    := 
PreprocessOnlySwitch   :=-E
ObjectsFileList        :="Arvore_AVL.txt"
PCHCompileFlags        :=
MakeDirCommand         :=makedir
RcCmpOptions           := 
RcCompilerName         :=C:/MinGW-4.8.1/bin/windres.exe 
LinkOptions            :=  
IncludePath            :=  $(IncludeSwitch). $(IncludeSwitch). 
IncludePCH             := 
RcIncludePath          := 
Libs                   := 
ArLibs                 :=  
LibPath                := $(LibraryPathSwitch). 

##
## Common variables
## AR, CXX, CC, AS, CXXFLAGS and CFLAGS can be overriden using an environment variables
##
AR       := C:/MinGW-4.8.1/bin/ar.exe rcu
CXX      := C:/MinGW-4.8.1/bin/g++.exe 
CC       := C:/MinGW-4.8.1/bin/gcc.exe 
CXXFLAGS :=  -g -O0 -Wall $(Preprocessors)
CFLAGS   :=  -g -O0 -Wall $(Preprocessors)
ASFLAGS  := 
AS       := C:/MinGW-4.8.1/bin/as.exe 


##
## User defined environment variables
##
CodeLiteDir:=C:\Program Files\CodeLite
UNIT_TEST_PP_SRC_DIR:=C:\UnitTest++-1.3
Objects0=$(IntermediateDirectory)/main.c$(ObjectSuffix) $(IntermediateDirectory)/Arvore_binaria.c$(ObjectSuffix) $(IntermediateDirectory)/AVL.c$(ObjectSuffix) 



Objects=$(Objects0) 

##
## Main Build Targets 
##
.PHONY: all clean PreBuild PrePreBuild PostBuild
all: $(OutputFile)

$(OutputFile): $(IntermediateDirectory)/.d $(Objects) 
	@$(MakeDirCommand) $(@D)
	@echo "" > $(IntermediateDirectory)/.d
	@echo $(Objects0)  > $(ObjectsFileList)
	$(LinkerName) $(OutputSwitch)$(OutputFile) @$(ObjectsFileList) $(LibPath) $(Libs) $(LinkOptions)

$(IntermediateDirectory)/.d:
	@$(MakeDirCommand) "./Debug"

PreBuild:


##
## Objects
##
$(IntermediateDirectory)/main.c$(ObjectSuffix): main.c $(IntermediateDirectory)/main.c$(DependSuffix)
	$(CC) $(SourceSwitch) "C:/Users/LAB4-PROFESSOR/Documents/GitHub/Trabalho-2-ProjAlgoritmosII/Arvore_AVL/main.c" $(CFLAGS) $(ObjectSwitch)$(IntermediateDirectory)/main.c$(ObjectSuffix) $(IncludePath)
$(IntermediateDirectory)/main.c$(DependSuffix): main.c
	@$(CC) $(CFLAGS) $(IncludePath) -MG -MP -MT$(IntermediateDirectory)/main.c$(ObjectSuffix) -MF$(IntermediateDirectory)/main.c$(DependSuffix) -MM "main.c"

$(IntermediateDirectory)/main.c$(PreprocessSuffix): main.c
	@$(CC) $(CFLAGS) $(IncludePath) $(PreprocessOnlySwitch) $(OutputSwitch) $(IntermediateDirectory)/main.c$(PreprocessSuffix) "main.c"

$(IntermediateDirectory)/Arvore_binaria.c$(ObjectSuffix): Arvore_binaria.c $(IntermediateDirectory)/Arvore_binaria.c$(DependSuffix)
	$(CC) $(SourceSwitch) "C:/Users/LAB4-PROFESSOR/Documents/GitHub/Trabalho-2-ProjAlgoritmosII/Arvore_AVL/Arvore_binaria.c" $(CFLAGS) $(ObjectSwitch)$(IntermediateDirectory)/Arvore_binaria.c$(ObjectSuffix) $(IncludePath)
$(IntermediateDirectory)/Arvore_binaria.c$(DependSuffix): Arvore_binaria.c
	@$(CC) $(CFLAGS) $(IncludePath) -MG -MP -MT$(IntermediateDirectory)/Arvore_binaria.c$(ObjectSuffix) -MF$(IntermediateDirectory)/Arvore_binaria.c$(DependSuffix) -MM "Arvore_binaria.c"

$(IntermediateDirectory)/Arvore_binaria.c$(PreprocessSuffix): Arvore_binaria.c
	@$(CC) $(CFLAGS) $(IncludePath) $(PreprocessOnlySwitch) $(OutputSwitch) $(IntermediateDirectory)/Arvore_binaria.c$(PreprocessSuffix) "Arvore_binaria.c"

$(IntermediateDirectory)/AVL.c$(ObjectSuffix): AVL.c $(IntermediateDirectory)/AVL.c$(DependSuffix)
	$(CC) $(SourceSwitch) "C:/Users/LAB4-PROFESSOR/Documents/GitHub/Trabalho-2-ProjAlgoritmosII/Arvore_AVL/AVL.c" $(CFLAGS) $(ObjectSwitch)$(IntermediateDirectory)/AVL.c$(ObjectSuffix) $(IncludePath)
$(IntermediateDirectory)/AVL.c$(DependSuffix): AVL.c
	@$(CC) $(CFLAGS) $(IncludePath) -MG -MP -MT$(IntermediateDirectory)/AVL.c$(ObjectSuffix) -MF$(IntermediateDirectory)/AVL.c$(DependSuffix) -MM "AVL.c"

$(IntermediateDirectory)/AVL.c$(PreprocessSuffix): AVL.c
	@$(CC) $(CFLAGS) $(IncludePath) $(PreprocessOnlySwitch) $(OutputSwitch) $(IntermediateDirectory)/AVL.c$(PreprocessSuffix) "AVL.c"


-include $(IntermediateDirectory)/*$(DependSuffix)
##
## Clean
##
clean:
	$(RM) ./Debug/*$(ObjectSuffix)
	$(RM) ./Debug/*$(DependSuffix)
	$(RM) $(OutputFile)
	$(RM) $(OutputFile).exe
	$(RM) "../../Netflix/Programa/Netflix/Trabalho/.build-debug/Arvore_AVL"


