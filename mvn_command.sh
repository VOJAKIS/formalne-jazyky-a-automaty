
group_id="sk.umb.fpv.valastan.formalne_jazyky"
artifact_id="zadanie1"

mvn archetype:generate \
	-DgroupId=${group_id} \
	-DartifactId=${artifact_id} \
	-DarchetypeArtifactId=maven-template \
	-DinteractiveMode=false