/* 
 * Author: Duncan Woodbury
 * Date:	 12-05-2016
 * This file provides a method to generate arbitrary battleship boards in an effort
 *  to evaluate the probabilities of each ship(carrier(5), cruiser(3), bship(4), sub(3), destr(2))
 *  occurring at any given space.
 * A serial implementation will be provided (so this can be translated to java), then a 
 *  parallel method compatible with MPI is given.
 */

/*
 * TODO:  generate a board based on known information k[] 
 *				determine relative efficiency
 *				* Convert to parallel code for maya
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include <time.h>

//#ifdef PARALLEL
#include "mpi.h"
//#endif

double *allocate_double_vector (int n) {
  double *x;

  x = (double*) calloc (n, sizeof(double));

  if (x == NULL) {
    fprintf (stderr, "Problem allocating memory for vector\n");
#ifdef PARALLEL
    MPI_Abort (MPI_COMM_WORLD, 1);
#else
    exit (1);
#endif
  }

  return x;
}

int *allocate_int_vector (int n) {
  int *x;

  x = (int*) calloc (n, sizeof(int));

  if (x == NULL) {
    fprintf (stderr, "Problem allocating memory for vector\n");
#ifdef PARALLEL
    MPI_Abort (MPI_COMM_WORLD, 1);
#else
    exit (1);
#endif
  }
  return x;
}

/* randomly shuffle array of size n */
/* implements the fisher-yates shuffle */
void shuffleArr(int * arr, int n) {
	for(int i = n-1; i > 0; i--) {
		int ind = rand() % (i+1);
		int t = arr[i];
		arr[i] = arr[ind];
		arr[ind] = t;
	}
}

int posIsValid(int pos, int l_size, int dir, int orient, int N, int * used) {
	int y, sq;
	for(y = 0; y < l_size; y++) { 
		sq = (orient == 10) ? (pos + (-1*N*dir*y)) : (pos + dir*y);
		if(used[sq]) { return 0; }
	}
	return 1;
}

/*
 * Generates a test board and adds the respective probailities of each ship to
 *  the ships[] vector
 * Input: ships[] where 0 = destroyer, 1=sub OR cruiser (equal prob), 2=bship, 3=carrier
 * 				int N = dimension of the board (default 10)
 *				int n = # boards to generate
 */
void generateBoard(double * ships, int N, int n, int np) {
	int * sizes = allocate_int_vector(5);
	sizes[0] = 2;
	sizes[1] = 3;
	sizes[2] = 3;	/* done explcitly to make shuffling easier - one alloc v n */
	sizes[3] = 4;
	sizes[4] = 5; 
	
	int * used;
	used = allocate_int_vector(N*N);
	int i, z, y, w, sq, dir, orient, l_size, pos, u;
	int valid;
	double avgfactor = (1.0/(n*np));
  srand(time(NULL));

		for(i = 0; i < n; i++) {			
		/* place 5 ships */

		/* Randomly */
		shuffleArr(sizes, 5); 
		
		for(z = 0; z < 5; z++) {
			valid = 0;
			/* select size */
			l_size = sizes[z];
			/* ==> take random direction */
			dir = pow(-1, rand());
			orient = (rand() % 2) ? 1 : 10 ; /* 1 csp to left/right, 10 to up/down */
			/* select position on board */
			/* string together ternary operator for bounds checking */
			while(!valid) {
					pos = (orient==1)  ? ((dir>0) ? (rand() % (N-l_size+1))*(rand() % N)  : (((l_size-1)+(rand() % (N-l_size+1)))*(rand() % N))) : ((dir>0) ? ((N*(l_size-1)) + (rand() % ((N*N)-(N*(l_size-1))))) : (rand() % (N*N-(N*l_size-1)))); 
				
				valid = posIsValid(pos, l_size, dir, orient, N, used);
			} /* should be valid now */
				printf("BOARD i=%d size = %d orient = %d dir = %d pos = %d \n\n",i,l_size,orient,dir,pos);
			
			/* if valid, fill appropriate squares with 1 AND  
			 *  update ships(position(0..99)*10 + size) by 1/n
			 */
			for(w = 0; w < l_size; w++) { 
				sq = (orient == 10) ? (pos + (N*-1*dir*w)) :  (pos + (dir*w));
				used[sq] = 1;
				ships[(l_size-2)+(sq*4)] += ( (l_size == 3) ? avgfactor*(1.0/2.0) : avgfactor ); 
			}
		}
		memset(used,0,N*N*sizeof(int));
	
		}
	 
	free(used);

	
}

int main(int argc, char *argv[]) {
	
  int id, np, processor_name_len;
  char processor_name[MPI_MAX_PROCESSOR_NAME];
  char message[100];
  MPI_Status status;
	
  MPI_Init(&argc, &argv);

  MPI_Comm_size(MPI_COMM_WORLD, &np);
  MPI_Comm_rank(MPI_COMM_WORLD, &id);

	int nships = 4;
	int N = 10; /* board dimension ,size=N*N */
	double * ships;


	int n = 64; /* number of boards to be generated */

	ships = allocate_double_vector(N*N*nships); /* keep track of ship probs */
	memset(ships, 0, N*N*nships);
	
	generateBoard(ships, N, n, np);

	//int i;
	//for(i = 0; i < (N*N*nships); i++) {
//		if(i % nships == 0) { printf("\n"); }
//		printf(" %f ", ships[i]);
	//}
	 
	double * fullres;
	int u, v;
	double sum;
	
	if(id == 0) {
		fullres = allocate_double_vector(N*N*nships);
		MPI_Reduce(&ships, &fullres, N*N*nships, MPI_DOUBLE, MPI_SUM, 0, MPI_COMM_WORLD);
		
		for(u = 0; u < nships; u++) { // confirm model adds properly
			sum = 0;
			for(v = 0; v < N*N; v++) {
				sum += (ships[v*nships + u]/(u+2));
			}
			printf("sum for col %d is %f\n",u,sum);
		} printf(" \n");
		free(fullres); 
	}

	free(ships);
	
  MPI_Finalize();

  return 0;
	
}


























