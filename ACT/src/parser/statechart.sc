SYSTEM : sys{
	
	STATE : s1{}
	STATE : s2{} 
	
	TRANSITION : t1{
		SOURCE : s1
		DEST : s2
		GUARD : t=10
		ACTI : t=11
		  }
}
